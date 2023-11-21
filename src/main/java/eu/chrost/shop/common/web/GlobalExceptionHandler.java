package eu.chrost.shop.common.web;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import eu.chrost.shop.orders.OrderNotFoundException;
import eu.chrost.shop.products.ProductNotFoundException;
import java.util.Locale;

import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final MessageSource messageSource;

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDto> onProductNotFoundException(ProductNotFoundException e, Locale locale) {
        String exceptionClassName = e.getClass().getSimpleName();
        String description = messageSource.getMessage(exceptionClassName, new Object[] {1}, locale);
        return ResponseEntity.status(NOT_FOUND).body(new ExceptionDto(description));
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ExceptionDto> onOrderNotFoundException(OrderNotFoundException e, Locale locale) {
        String exceptionClassName = e.getClass().getSimpleName();
        String description = messageSource.getMessage(exceptionClassName, new Object[] {1}, locale);
        return ResponseEntity.status(NOT_FOUND).body(new ExceptionDto(description));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionDto> constraintViolationException(ConstraintViolationException e) {
        return ResponseEntity.status(BAD_REQUEST).body(new ExceptionDto(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDto> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(BAD_REQUEST).body(new ExceptionDto(e.getMessage()));
    }
}

package eu.chrost.shop.products;

import eu.chrost.shop.common.ExceptionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
@RequiredArgsConstructor
public class ProductsControllerAdvice {
    private final MessageSource messageSource;

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDto> onProductNotFoundException(ProductNotFoundException e) {
        String description = messageSource.getMessage(ProductNotFoundException.class.getSimpleName(), new Object[] {e.getId()}, Locale.getDefault());
        return ResponseEntity.status(NOT_FOUND).body(new ExceptionDto(description));
    }
}

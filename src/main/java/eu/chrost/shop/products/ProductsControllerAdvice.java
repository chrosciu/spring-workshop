package eu.chrost.shop.products;

import eu.chrost.shop.common.ExceptionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductsControllerAdvice {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDto> onProductNotFoundException(ProductNotFoundException e) {
        return ResponseEntity.unprocessableEntity().body(new ExceptionDto(e.getMessage()));
    }
}

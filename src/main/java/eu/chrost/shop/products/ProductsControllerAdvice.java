package eu.chrost.shop.products;

import eu.chrost.shop.common.ExceptionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ProductsControllerAdvice {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDto> onProductNotFoundException(ProductNotFoundException e) {
        return ResponseEntity.status(NOT_FOUND).body(new ExceptionDto("Product not found"));
    }
}

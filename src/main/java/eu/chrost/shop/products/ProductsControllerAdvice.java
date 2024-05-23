package eu.chrost.shop.products;


import eu.chrost.shop.common.ExceptionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@ControllerAdvice
public class ProductsControllerAdvice {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDto> onProductNotFoundException(ProductNotFoundException e) {
        return ResponseEntity.status(UNPROCESSABLE_ENTITY).body(new ExceptionDto("Product not found"));
    }
}

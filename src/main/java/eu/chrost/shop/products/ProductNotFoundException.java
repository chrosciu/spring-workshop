package eu.chrost.shop.products;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductNotFoundException extends RuntimeException {
    @Getter
    private final long id;
}

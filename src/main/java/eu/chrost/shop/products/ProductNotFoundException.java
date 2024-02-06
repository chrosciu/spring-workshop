package eu.chrost.shop.products;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(long id) {
        super(String.format("Product with id %d not found", id));
    }
}

package eu.chrost.shop.products;

import java.util.Optional;

public interface CustomProductRepository {
    Optional<Product> findSingleWithDescription(String description);
}

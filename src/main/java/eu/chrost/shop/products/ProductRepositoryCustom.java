package eu.chrost.shop.products;

import java.util.Optional;

public interface ProductRepositoryCustom {
    Optional<Product> findByDescription(String description);
}

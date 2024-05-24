package eu.chrost.shop.products;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CustomProductRepositoryImpl implements CustomProductRepository {

    private final EntityManager entityManager;

    @Override
    public Optional<Product> findSingleWithDescription(String description) {
        try {
            return Optional.of(entityManager
                    .createQuery("SELECT p FROM Product p WHERE p.description = :description", Product.class)
                    .setParameter("description", description)
                    .getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}

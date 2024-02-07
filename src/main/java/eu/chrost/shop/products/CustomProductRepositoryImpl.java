package eu.chrost.shop.products;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CustomProductRepositoryImpl implements CustomProductRepository {
    private final EntityManager entityManager;

    @Override
    public Optional<Product> findByDescription(String description) {
        try {
            return Optional.of(entityManager
                    .createQuery("select p from Product p where p.description = :description", Product.class)
                    .setParameter("description", description)
                    .getSingleResult()
            );
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}

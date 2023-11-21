package eu.chrost.shop.products;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {
    private final EntityManager entityManager;

    @Override
    public Optional<Product> findByDescription(String description) {
        Optional<Product> result;
        try {
            result = Optional.of(entityManager.createQuery("select p from Product p where p.description = :description", Product.class)
                    .setParameter("description", description)
                    .getSingleResult());
        } catch (NoResultException exception) {
            result = Optional.empty();
        }
        return result;
    }

}

package eu.chrost.shop.products;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
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
        } catch (NoResultException ne) {
            //this is correct if no product with given description exists
        } catch (Exception e) {
            log.warn("", e);
        }
        return Optional.empty();
    }
}

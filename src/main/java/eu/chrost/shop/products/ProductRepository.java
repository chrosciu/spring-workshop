package eu.chrost.shop.products;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameContaining(String name);

    Page<Product> findByNameContaining(String name, Pageable pageable);

    @Query("from Product p where p.type = :type")
    List<Product> findByType(/*@Param("type")*/ ProductType type);
}

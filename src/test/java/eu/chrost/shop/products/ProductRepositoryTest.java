package eu.chrost.shop.products;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Sql("/products.sql")
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldFindAllProducts() {
        //when
        var products = productRepository.findAll();

        //then
        assertThat(products).extracting(Product::getId)
                .containsExactly(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L);
    }

    @Test
    void shouldFindAllProductsWithNameContainingGivenPhrase() {
        //when
        var products = productRepository.findByNameContaining("masterclass");

        //then
        assertThat(products).extracting(Product::getId)
                .containsExactly(1L, 3L, 7L, 9L);
    }

    @Test
    void shouldFindAllProductsWithNameContainingGivenPhrasePaging() {
        //when
        var products = productRepository.findByNameContaining(
                "masterclass",
                PageRequest.of(1, 2, Sort.by(Sort.Direction.DESC, "id"))
        );

        //then
        assertThat(products).extracting(Product::getId)
                .containsExactly(3L, 1L);
        assertThat(products.getTotalElements()).isEqualTo(4);
    }

    @Test
    void shouldFindAllProductsWithGivenType() {
        //when
        var videoProducts = productRepository.findByType(ProductType.VIDEO);

        //then
        assertThat(videoProducts).extracting(Product::getId)
                .containsExactly(1L, 3L, 7L);
    }

    @Test
    void shouldFindSingleProductWithGivenDescription() {
        //when
        var maybeProduct = productRepository.findByDescription("Blah");

        //then
        assertThat(maybeProduct).isEmpty();
    }
}

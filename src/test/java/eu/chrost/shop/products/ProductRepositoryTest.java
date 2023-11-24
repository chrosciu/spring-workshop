package eu.chrost.shop.products;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
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
        var products = productRepository.findByNameContaining("pod");

        //then
        assertThat(products).extracting(Product::getId)
                .containsExactly(5L, 8L);
    }

    @Test
    void shouldFindAllProductsWithNameContainingGivenPhrasePaging() {
        //when
        var products = productRepository.findByNameContaining("pod", Pageable.ofSize(1));

        //then
        assertThat(products.getContent()).extracting(Product::getId)
                .containsExactly(5L);

        assertThat(products.getTotalElements()).isEqualTo(2);
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

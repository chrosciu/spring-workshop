package eu.chrost.shop.products;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

import static eu.chrost.shop.products.ProductType.AUDIO;
import static eu.chrost.shop.products.ProductType.VIDEO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@Sql("/products.sql")
@AutoConfigureTestDatabase(replace = NONE)
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldFindAllProducts() {
        //when
        var allProducts = productRepository.findAll();

        //then
        assertThat(allProducts).hasSize(2);
    }

    @Test
    void shouldFindProductsByType() {
        //given
        var videoType = VIDEO;

        //when
        var videoProducts = productRepository.findByType(videoType);

        //then
        assertThat(videoProducts).hasSize(1);
        assertThat(videoProducts).extracting(Product::getType).containsExactly(VIDEO);
    }

    @Test
    void shouldFindProductsByMaxPrice() {
        //given
        var maxPrice = 300L;

        //when
        var productsUpToMaxPrice = productRepository.findByMaxPrice(maxPrice);

        //then
        assertThat(productsUpToMaxPrice).hasSize(1);
        assertThat(productsUpToMaxPrice.get(0).getPrice()).isEqualTo(BigDecimal.valueOf(200));
    }

    @Test
    void shouldAddProduct() {
        //given
        var audioProduct = new Product();
        audioProduct.setName("Spring lepszy niż tabletki nasenne");
        audioProduct.setType(AUDIO);
        productRepository.save(audioProduct);

        //when
        var audioProducts = productRepository.findByType(AUDIO);

        //then
        assertThat(audioProducts).contains(audioProduct);
    }
}

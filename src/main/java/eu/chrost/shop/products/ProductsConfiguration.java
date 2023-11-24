package eu.chrost.shop.products;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductsConfiguration {

    @Bean
    public ProductService productService(ProductRepository productRepository) {
        return new ProductService(productRepository);
    }

    @Bean
    public ProductMapper productMapper() {
        return new ProductMapper();
    }
}

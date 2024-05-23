package eu.chrost.shop;

import eu.chrost.shop.products.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class ShopApplicationTest {
    @Autowired
    private ProductService productService;

    @Test
    void contextLoads() {}

}

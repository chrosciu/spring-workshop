package eu.chrost.shop;

import eu.chrost.shop.orders.Order;
import eu.chrost.shop.payments.Payment;
import eu.chrost.shop.payments.PaymentStatus;
import eu.chrost.shop.products.Product;
import eu.chrost.shop.products.ProductService;
import eu.chrost.shop.products.ProductType;
import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ShopIntegrationTest {
    @Autowired
    private ProductService productService;
    @Autowired
    private ShopService shopService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void itShouldBePossibleToAddProductsToShop() {
        productService.add(videoProductSupplier.get());
        List<Product> allProducts = productService.getAll();
        assertEquals(1, allProducts.size());
        assertEquals("Praktyczny kurs Spring framework",
            allProducts.get(0).getDescription());
    }

    @Test
    public void itShouldBePossibleToPlaceOrderAndInitiatePayment() {
        Product videoProduct = videoProductSupplier.get();
        Product bookProduct = bookProductSupplier.get();
        shopService.addProduct(videoProduct);
        shopService.addProduct(bookProduct);
        List<Product> products = new ArrayList<>();
        products.add(videoProduct);
        products.add(bookProduct);
        Order order = new Order(products);
        shopService.placeOrder(order);
        Payment payment = shopService.payForOrder(order.getId());
        assertEquals(BigDecimal.valueOf(1700), payment.getMoney());
        assertEquals(PaymentStatus.STARTED, payment.getStatus());
    }

    private static Supplier<Product> videoProductSupplier = () -> Product.builder()
            .name("Spring masterclass")
            .description("Praktyczny kurs Spring framework")
            .type(ProductType.VIDEO)
            .price(BigDecimal.valueOf(1500))
            .build();

    private static Supplier<Product> bookProductSupplier = () -> Product.builder()
            .name("Spring guide")
            .description("Praktyczne ćwiczenia do samodzielnego wykonania")
            .type(ProductType.BOOK)
            .price(BigDecimal.valueOf(200))
            .build();

}

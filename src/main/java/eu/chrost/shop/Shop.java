package eu.chrost.shop;

import eu.chrost.shop.orders.Order;
import eu.chrost.shop.products.Product;
import eu.chrost.shop.products.ProductType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
public class Shop {
    private static final Product VIDEO_PRODUCT = Product.builder()
            .name("Spring masterclass")
            .description("Praktyczny kurs Spring framework")
            .type(ProductType.VIDEO)
            .price(BigDecimal.valueOf(1500))
            .build();

    private static final Product BOOK_PRODUCT = Product.builder()
            .name("Spring guide")
            .description("Praktyczne ćwiczenia do samodzielnego wykonania")
            .type(ProductType.BOOK)
            .price(BigDecimal.valueOf(200))
            .build();

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ShopConfiguration.class)) {
            var shopService = applicationContext.getBean(ShopService.class);
            shopService.addProduct(VIDEO_PRODUCT);
            shopService.addProduct(BOOK_PRODUCT);
            log.info(shopService.getProducts().toString());

            var order = new Order(List.of(VIDEO_PRODUCT, BOOK_PRODUCT));
            shopService.placeOrder(order);
            var payment = shopService.payForOrder(order.getId());
            log.info(payment.getId());
        }
    }
}

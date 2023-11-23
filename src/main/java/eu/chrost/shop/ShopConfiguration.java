package eu.chrost.shop;

import eu.chrost.shop.orders.OrderService;
import eu.chrost.shop.payments.PaymentService;
import eu.chrost.shop.products.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@Configuration
public class ShopConfiguration {
    @Bean
    public ShopService shopService(OrderService orderService, PaymentService paymentService, ProductService productService) {
        return new ShopService(orderService, paymentService, productService);
    }
}
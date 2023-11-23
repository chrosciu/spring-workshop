package eu.chrost.shop;

import eu.chrost.shop.orders.OrderService;
import eu.chrost.shop.payments.PaymentService;
import eu.chrost.shop.products.ProductService;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAspectJAutoProxy
@EnableAsync
@Configuration
@ComponentScan
@ConfigurationPropertiesScan
public class ShopConfiguration {
    @Bean
    public ShopService shopService(OrderService orderService, PaymentService paymentService, ProductService productService) {
        return new ShopService(orderService, paymentService, productService);
    }

//    @Bean(name = "threadPoolTaskExecutor")
//    public Executor getAsyncExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setMaxPoolSize(100);
//        executor.initialize();
//        return executor;
//    }

    @Bean
    public ShopRunner shopRunner(ShopService shopService) {
        return new ShopRunner(shopService);
    }
}

package eu.chrost.shop.orders;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class OrdersConfiguration {
    @Bean
    public OrderService orderService(OrderRepository orderRepository, ApplicationEventPublisher eventPublisher) {
        return new OrderService(orderRepository, eventPublisher);
    }

    @Bean
    @Profile("audit")
    public OrderAuditListener orderAuditListener(@Value("${order.audit.logPrefix}") String logPrefix) {
        return new OrderAuditListener(logPrefix);
    }
}

package eu.chrost.shop.orders;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrdersConfiguration {
    @Bean
    public OrderRepository orderRepository() {
        return new HashMapOrderRepository();
    }

    @Bean
    public OrderService orderService(OrderRepository orderRepository, ApplicationEventPublisher applicationEventPublisher) {
        return new OrderService(orderRepository, applicationEventPublisher);
    }

    @Bean
    @ConditionalOnProperty("order.audit.enabled")
    public OrderAuditListener orderAuditListener(OrderAuditProperties properties) {
        return new OrderAuditListener(properties.logPrefix());
    }
}

package eu.chrost.shop.orders;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

import static eu.chrost.shop.orders.OrderAuditEventType.CREATE;
import static eu.chrost.shop.orders.OrderAuditEventType.UPDATE;

@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public Order add(Order order) {
        var savedOrder = orderRepository.save(order);
        applicationEventPublisher.publishEvent(new OrderAuditEvent(this, CREATE, savedOrder));
        return savedOrder;
    }

    public Order getBy(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(OrderNotFoundException::new);
    }

    public void update(Order order) {
        applicationEventPublisher.publishEvent(new OrderAuditEvent(this, UPDATE, order));
        orderRepository.save(order);
    }
}

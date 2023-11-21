package eu.chrost.shop.orders;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher eventPublisher;

    public Order add(Order order) {
        var savedOrder = orderRepository.save(order);
        eventPublisher.publishEvent(new OrderAuditEvent(this, OrderAuditEventType.CREATE, savedOrder));
        return savedOrder;
    }

    public Order getBy(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    public void update(Order order) {
        eventPublisher.publishEvent(new OrderAuditEvent(this, OrderAuditEventType.UPDATE, order));
        orderRepository.save(order);
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }
}

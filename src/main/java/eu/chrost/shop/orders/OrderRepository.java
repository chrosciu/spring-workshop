package eu.chrost.shop.orders;

import eu.chrost.shop.payments.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("from Order o join fetch o.payment p where p.status = :paymentStatus")
    List<Order> findByPaymentStatus(PaymentStatus paymentStatus);
}

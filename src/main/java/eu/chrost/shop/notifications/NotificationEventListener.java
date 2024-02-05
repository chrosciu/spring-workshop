package eu.chrost.shop.notifications;

import eu.chrost.shop.payments.PaymentStatusChangedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

@Slf4j
public class NotificationEventListener {
    @EventListener
    public void onPaymentStatusChange(PaymentStatusChangedEvent statusChangedEvent) {
        log.info("Payment changed status: {}", statusChangedEvent.getPayment());
    }
}

package eu.chrost.shop.payments;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;

@Slf4j
public class PaymentStatusChangeListener implements ApplicationListener<PaymentStatusChangedEvent> {
//    @EventListener
//    public void onPaymentStatusChange(PaymentStatusChangedEvent statusChangedEvent) {
//        log.info("Payment changed status: {}", statusChangedEvent.getPayment());
//    }

    @Override
    public void onApplicationEvent(PaymentStatusChangedEvent statusChangedEvent) {
        log.info("Payment changed status: {}", statusChangedEvent.getPayment());
    }
}

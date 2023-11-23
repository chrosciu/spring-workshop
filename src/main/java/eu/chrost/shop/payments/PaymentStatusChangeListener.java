package eu.chrost.shop.payments;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

@Slf4j
public class PaymentStatusChangeListener {
    @EventListener
//    @Async("threadPoolTaskExecutor")
    public void onPaymentStatusChange(PaymentStatusChangedEvent statusChangedEvent) {
        log.info("Payment changed status: {}", statusChangedEvent.getPayment());
    }

//    @Override
//    public void onApplicationEvent(PaymentStatusChangedEvent statusChangedEvent) {
//        log.info("Payment changed status: {}", statusChangedEvent.getPayment());
//    }
}

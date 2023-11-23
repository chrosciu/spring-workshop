package eu.chrost.shop.payments;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;

@Slf4j
public class PaymentStatusChangeListener implements ApplicationListener<PaymentStatusChangedEvent> {
//    @EventListener
//    @Async("threadPoolTaskExecutor")
//    public void onPaymentStatusChange(PaymentStatusChangedEvent statusChangedEvent) {
//        log.info("Payment changed status: {}", statusChangedEvent.getPayment());
//    }

    @Override
    @Async("threadPoolTaskExecutor")
    public void onApplicationEvent(PaymentStatusChangedEvent statusChangedEvent) {
        log.info("Payment changed status: {}", statusChangedEvent.getPayment());
    }
}

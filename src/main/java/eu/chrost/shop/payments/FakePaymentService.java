package eu.chrost.shop.payments;

import eu.chrost.shop.common.Retry;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

import java.time.Instant;

@RequiredArgsConstructor
@Slf4j
public class FakePaymentService implements PaymentService {
    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentRepository paymentRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @LogPayments
    @Retry(attempts = 2)
    @Override
    public Payment process(PaymentRequest paymentRequest) {
        var payment = Payment.builder()
                .id(paymentIdGenerator.getNext())
                .money(paymentRequest.getMoney())
                .timestamp(Instant.now())
                .status(PaymentStatus.STARTED)
                .build();
        var event = new PaymentStatusChangedEvent(this, payment);
        applicationEventPublisher.publishEvent(event);
        return paymentRepository.save(payment);
    }

    @PostConstruct
    void init() {
        log.info("Bean has been created");
    }

    @PreDestroy
    void destroy() {
        log.info("Bean is to be destroyed");
    }


}

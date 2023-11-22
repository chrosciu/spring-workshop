package eu.chrost.shop.payments;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;

@RequiredArgsConstructor
@Component
@Slf4j
public class FakePaymentService implements PaymentService {
    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentRepository paymentRepository;

    @LogPayments
    public Payment process(PaymentRequest paymentRequest) {
        var payment = Payment.builder()
                .id(paymentIdGenerator.getNext())
                .money(paymentRequest.getMoney())
                .timestamp(Instant.now())
                .status(PaymentStatus.STARTED)
                .build();
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

package eu.chrost.shop.payments;

import eu.chrost.shop.common.Retry;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.Instant;

//@RequiredArgsConstructor
@Component
@Slf4j
public class FakePaymentService implements PaymentService {
    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentRepository paymentRepository;

    public FakePaymentService(@Qualifier("uuid") PaymentIdGenerator paymentIdGenerator, PaymentRepository paymentRepository) {
        this.paymentIdGenerator = paymentIdGenerator;
        this.paymentRepository = paymentRepository;
    }

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

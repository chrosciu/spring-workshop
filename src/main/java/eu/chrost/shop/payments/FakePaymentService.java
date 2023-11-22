package eu.chrost.shop.payments;

import eu.chrost.shop.common.AroundTestAnnotation;
import eu.chrost.shop.common.Retry;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Component;

import java.time.Instant;

@RequiredArgsConstructor
@Component
@Slf4j
public class FakePaymentService implements PaymentService {
    private final ObjectFactory<PaymentIdGenerator> paymentIdGeneratorFactory;
    private final PaymentRepository paymentRepository;

    @LogPayments
    @Retry(attempts = 2)
    @AroundTestAnnotation
    @Override
    public Payment process(PaymentRequest paymentRequest) {
        var paymentIdGenerator = paymentIdGeneratorFactory.getObject();
        log.info("{}", paymentIdGenerator);
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

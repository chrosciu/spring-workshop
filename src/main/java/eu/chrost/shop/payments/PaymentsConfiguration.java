package eu.chrost.shop.payments;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentsConfiguration {
    @Bean
    @Qualifier("incremental")
    public PaymentIdGenerator incrementalPaymentIdGenerator() {
        return new IncrementalPaymentIdGenerator();
    }

    @Bean
    @UUIDQualifier
    public PaymentIdGenerator uuidPaymentIdGenerator() {
        return new UUIDPaymentIdGenerator();
    }

    @Bean
    public PaymentRepository paymentRepository() {
        return new HashMapPaymentRepository();
    }

    @Bean
    public PaymentService fakePaymentService(@UUIDQualifier PaymentIdGenerator paymentIdGenerator, PaymentRepository paymentRepository, ApplicationEventPublisher applicationEventPublisher) {
        return new FakePaymentService(paymentIdGenerator, paymentRepository, applicationEventPublisher);
    }

    @Bean
    public PaymentConsoleLogger paymentConsoleLogger() {
        return new PaymentConsoleLogger();
    }

    @Bean
    public PaymentStatusChangeListener paymentStatusChangeListener() {
        return new PaymentStatusChangeListener();
    }
}

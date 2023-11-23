package eu.chrost.shop.payments;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentsConfiguration {
    @Bean
    //@Qualifier("incremental")
    //@Profile("!uuid")
    @ConditionalOnProperty(value = "uuid.generator.enabled", havingValue = "false")
    public PaymentIdGenerator incrementalPaymentIdGenerator(@Value("${incremental.generator.initial:10}") long initialValue) {
        var generator = new IncrementalPaymentIdGenerator();
        generator.setIndex(initialValue);
        return generator;
    }

    @Bean
    //@UUIDQualifier
    //@Profile("uuid")
    @ConditionalOnProperty("uuid.generator.enabled")
    public PaymentIdGenerator uuidPaymentIdGenerator() {
        return new UUIDPaymentIdGenerator();
    }

    @Bean
    public PaymentRepository paymentRepository() {
        return new HashMapPaymentRepository();
    }

    @Bean
    public PaymentService fakePaymentService(/*@UUIDQualifier*/ PaymentIdGenerator paymentIdGenerator, PaymentRepository paymentRepository, ApplicationEventPublisher applicationEventPublisher) {
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

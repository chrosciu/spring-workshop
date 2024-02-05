package eu.chrost.shop.payments;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class PaymentsConfiguration {
    @Bean
    //@IncrementalQualifier
    @ConditionalOnProperty(value = "uuid.generator.enabled", havingValue = "false")
    public PaymentIdGenerator incrementalPaymentIdGenerator(IncrementalPaymentIdGeneratorProperties properties) {
        var generator = new IncrementalPaymentIdGenerator();
        generator.setIndex(properties.initial());
        return generator;
    }

    @Bean
    //@UUIDQualifier
    @ConditionalOnProperty("uuid.generator.enabled")
    public PaymentIdGenerator uuidPaymentIdGenerator() {
        return new UUIDPaymentIdGenerator();
    }

    @Bean
    public PaymentRepository paymentRepository() {
        return new HashMapPaymentRepository();
    }

    @Bean
    public PaymentService fakePaymentService(
            PaymentIdGenerator paymentIdGenerator,
            PaymentRepository paymentRepository,
            ApplicationEventPublisher applicationEventPublisher) {
        return new FakePaymentService(paymentIdGenerator, paymentRepository, applicationEventPublisher);
    }

    @Bean
    public PaymentConsoleLogger paymentConsoleLogger() {
        return new PaymentConsoleLogger();
    }
}

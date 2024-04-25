package eu.chrost.shop.payments;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(IncrementalPaymentIdGeneratorProperties.class)
class PaymentsConfiguration {
    @Bean
    @ConditionalOnProperty(value = "uuid.generator.enabled", havingValue = "false")
    public PaymentIdGenerator incrementalPaymentIdGenerator(IncrementalPaymentIdGeneratorProperties generatorProperties) {
        var generator = new IncrementalPaymentIdGenerator();
        generator.setIndex(generatorProperties.initial());
        generator.setStep(generatorProperties.step());
        return generator;
    }

    @Bean
    @ConditionalOnProperty("uuid.generator.enabled")
    public PaymentIdGenerator uuidPaymentIdGenerator() {
        return new UUIDPaymentIdGenerator();
    }

    @Bean
    public PaymentRepository paymentRepository() {
        return new HashMapPaymentRepository();
    }

    @Bean
    public PaymentService fakePaymentService(PaymentIdGenerator paymentIdGenerator, PaymentRepository paymentRepository, ApplicationEventPublisher applicationEventPublisher) {
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

package eu.chrost.shop.payments;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(IncrementalPaymentIdGeneratorProperties.class)
class PaymentsConfiguration {
    @Bean
    @ConditionalOnProperty(value = "uuid.generator.enabled", havingValue = "false")
    public PaymentIdGenerator incrementalPaymentIdGenerator(IncrementalPaymentIdGeneratorProperties generatorProperties) {
        var incrementalPaymentIdGenerator = new IncrementalPaymentIdGenerator();
        incrementalPaymentIdGenerator.setIndex(generatorProperties.initial());
        incrementalPaymentIdGenerator.setStep(generatorProperties.step());
        return incrementalPaymentIdGenerator;
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

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public PaymentService fakePaymentService(PaymentIdGenerator paymentIdGenerator, PaymentRepository paymentRepository) {
        return new FakePaymentService(paymentIdGenerator, paymentRepository);
    }
}

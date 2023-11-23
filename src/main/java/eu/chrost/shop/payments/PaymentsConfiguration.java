package eu.chrost.shop.payments;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class PaymentsConfiguration {
    @Bean
    @IncrementalQualifier
    public PaymentIdGenerator incrementalPaymentIdGenerator(IncrementalPaymentIdGeneratorProperties properties) {
        var generator = new IncrementalPaymentIdGenerator();
        generator.setIndex(properties.initial());
        return generator;
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
    public PaymentService fakePaymentService(@IncrementalQualifier PaymentIdGenerator paymentIdGenerator, PaymentRepository paymentRepository) {
        return new FakePaymentService(paymentIdGenerator, paymentRepository);
    }

    @Bean
    public PaymentConsoleLogger paymentConsoleLogger() {
        return new PaymentConsoleLogger();
    }
}

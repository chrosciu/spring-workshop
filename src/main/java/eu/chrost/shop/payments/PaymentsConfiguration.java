package eu.chrost.shop.payments;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;

@EnableAspectJAutoProxy
@Configuration
@ConfigurationPropertiesScan
public class PaymentsConfiguration {
    @Bean
    @Profile("!uuid")
    public PaymentIdGenerator paymentIdGenerator(IncrementalPaymentIdGeneratorProperties properties) {
        return new IncrementalPaymentIdGenerator(properties.getInitialValue(), properties.getStep());
    }

    @Bean
    @Profile("uuid")
    public PaymentIdGenerator uuidPaymentIdGenerator() {
        return new UUIDPaymentIdGenerator();
    }

    @Bean
    public PaymentService paymentService(PaymentIdGenerator paymentIdGenerator,
                                         PaymentRepository paymentRepository,
                                         ApplicationEventPublisher eventPublisher) {
        return new FakePaymentService(paymentIdGenerator, paymentRepository, eventPublisher);
    }

    @Bean
    public PaymentConsoleLogger paymentConsoleLogger() {
        return new PaymentConsoleLogger();
    }

    @Bean
    public PaymentStatusChangeListener paymentStatusChangeListener() {
        return new PaymentStatusChangeListener();
    }

    @Bean
    public PaymentStatusConverter paymentStatusConverter() {
        return new PaymentStatusConverter();
    }
}

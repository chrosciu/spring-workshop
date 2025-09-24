package eu.chrost.shop.payments;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBooleanProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(IncrementalPaymentIdGeneratorProperties.class)
class PaymentsConfiguration {
    @Bean
    @ConditionalOnBooleanProperty(value = "uuid.generator.enabled", havingValue = false)
    public PaymentIdGenerator incrementalPaymentIdGenerator(IncrementalPaymentIdGeneratorProperties generatorProperties) {
        var generator = new IncrementalPaymentIdGenerator();
        generator.setIndex(generatorProperties.initial());
        generator.setStep(generatorProperties.step());
        return generator;
    }

    @Bean
    @ConditionalOnBooleanProperty("uuid.generator.enabled")
    public PaymentIdGenerator uuidPaymentIdGenerator() {
        return new UUIDPaymentIdGenerator();
    }


    @Bean
    public PaymentService fakePaymentService(PaymentIdGenerator paymentIdGenerator,
                                             PaymentRepository paymentRepository,
                                             ApplicationEventPublisher applicationEventPublisher) {
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

    @Bean
    @ConfigurationProperties("spring.datasource.payments")
    public DataSourceProperties paymentsDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource paymentsDataSource() {
        return paymentsDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }
}

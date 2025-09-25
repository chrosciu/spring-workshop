package eu.chrost.shop.payments;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBooleanProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableConfigurationProperties(IncrementalPaymentIdGeneratorProperties.class)
@EnableJpaRepositories(
        basePackageClasses = Payment.class,
        entityManagerFactoryRef = "paymentsEntityManagerFactory",
        transactionManagerRef = "paymentsTransactionManager"
)
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

    @Bean
    public LocalContainerEntityManagerFactoryBean paymentsEntityManagerFactory(
            @Qualifier("paymentsDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages(Payment.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager paymentsTransactionManager(
            @Qualifier("paymentsEntityManagerFactory") LocalContainerEntityManagerFactoryBean paymentsEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(paymentsEntityManagerFactory.getObject()));
    }
}

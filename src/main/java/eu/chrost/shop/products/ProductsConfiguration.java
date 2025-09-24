package eu.chrost.shop.products;

import eu.chrost.shop.payments.Payment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableJpaRepositories(
        basePackageClasses = Product.class,
        entityManagerFactoryRef = "productsEntityManagerFactory",
        transactionManagerRef = "productsTransactionManager"
)
public class ProductsConfiguration {
    @Bean
    public ProductService productService(ProductRepository productRepository) {
        return new ProductService(productRepository);
    }

    @Bean
    @ConfigurationProperties("spring.datasource.products")
    public DataSourceProperties productsDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource productsDataSource() {
        return productsDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean productsEntityManagerFactory(
            @Qualifier("productsDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages(Product.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager productsTransactionManager(
            @Qualifier("productsEntityManagerFactory") LocalContainerEntityManagerFactoryBean productsEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(productsEntityManagerFactory.getObject()));
    }

}

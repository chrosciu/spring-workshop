package eu.chrost.shop.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {
    @Bean
    public RetryMethodExecutor retryMethodExecutor() {
        return new RetryMethodExecutor();
    }
}

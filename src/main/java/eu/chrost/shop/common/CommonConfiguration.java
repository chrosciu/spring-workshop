package eu.chrost.shop.common;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {
    @Bean
    public RetryMethodExecutor retryMethodExecutor() {
        return new RetryMethodExecutor();
    }

    @Bean
    @ConditionalOnProperty("request-logging.enabled")
    public FilterRegistrationBean<Slf4jRequestLoggingFilter> loggingFilter(RequestLoggingProperties properties) {
        FilterRegistrationBean<Slf4jRequestLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        Slf4jRequestLoggingFilter filter = new Slf4jRequestLoggingFilter();
        filter.setIncludeQueryString(properties.includeQueryString());
        filter.setIncludeHeaders(properties.includeHeaders());
        filter.setIncludePayload(properties.payload().include());
        filter.setMaxPayloadLength(properties.payload().maxLength());
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns(properties.urls().toArray(new String[0]));
        return registrationBean;
    }
}

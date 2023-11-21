package eu.chrost.shop.common;

import eu.chrost.shop.common.retry.RetryMethodExecutor;
import eu.chrost.shop.common.web.Slf4jRequestLoggingFilter;
import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository;
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.boot.actuate.web.exchanges.servlet.HttpExchangesFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.EnumSet;

import static org.springframework.boot.actuate.web.exchanges.Include.REMOTE_ADDRESS;
import static org.springframework.boot.actuate.web.exchanges.Include.REQUEST_HEADERS;
import static org.springframework.boot.actuate.web.exchanges.Include.RESPONSE_HEADERS;

@Configuration
@EnableConfigurationProperties(RequestLoggingProperties.class)
public class CommonConfiguration {
    @Bean
    public RetryMethodExecutor retryMethodExecutor() {
        return new RetryMethodExecutor();
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new AcceptHeaderLocaleResolver();
    }

    @Bean
    public HttpExchangeRepository httpExchangeRepository() {
        return new InMemoryHttpExchangeRepository();
    }

    @Bean
    public HttpExchangesFilter httpExchangesFilter(HttpExchangeRepository httpExchangeRepository) {
        return new HttpExchangesFilter(httpExchangeRepository, EnumSet.of(REQUEST_HEADERS, RESPONSE_HEADERS, REMOTE_ADDRESS));
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

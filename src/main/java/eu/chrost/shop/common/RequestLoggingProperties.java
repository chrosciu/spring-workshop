package eu.chrost.shop.common;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;


@ConfigurationProperties(prefix = "request-logging")
public record RequestLoggingProperties(
        boolean includeQueryString,
        boolean includeHeaders,
        RequestPayloadLoggingProperties payload,
        List<String> urls) {
    public record RequestPayloadLoggingProperties(boolean include, int maxLength) {}
}

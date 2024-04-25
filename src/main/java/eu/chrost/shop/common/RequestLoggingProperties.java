package eu.chrost.shop.common;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "request-logging")
record RequestLoggingProperties(
        boolean includeQueryString,
        boolean includeHeaders,
        RequestPayloadLoggingProperties payload,
        List<String> urls) {
    record RequestPayloadLoggingProperties(boolean include, int maxLength) {}
}

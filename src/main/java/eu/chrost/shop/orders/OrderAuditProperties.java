package eu.chrost.shop.orders;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "order.audit")
public record OrderAuditProperties(String logPrefix) {}

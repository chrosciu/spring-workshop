package eu.chrost.shop.payments;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "incremental.generator")
public record IncrementalPaymentIdGeneratorProperties(long initial) {}

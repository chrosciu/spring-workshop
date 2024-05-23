package eu.chrost.shop.payments;

import jakarta.validation.constraints.Positive;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "incremental.generator")
@Validated
public record IncrementalPaymentIdGeneratorProperties(@Positive long initial, @Positive long step) {}

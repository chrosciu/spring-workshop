package eu.chrost.shop.orders;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties(prefix = "order.audit")
public record OrderAuditProperties(@NotBlank @DefaultValue("AUDIT") String logPrefix) {}

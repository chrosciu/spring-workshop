package eu.chrost.shop.payments;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "incremental.generator")
@Value
public class IncrementalPaymentIdGeneratorProperties {
    long initialValue;
    int step;
}

package eu.chrost.shop.payments;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
@Value
public class Payment {
    String id;
    BigDecimal money;
    Instant timestamp;
    PaymentStatus status;
}

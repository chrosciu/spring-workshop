package eu.chrost.shop.payments;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Builder
@Value
public class PaymentRequest {
    Long id;
    BigDecimal money;
}

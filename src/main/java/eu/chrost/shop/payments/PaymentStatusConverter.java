package eu.chrost.shop.payments;

import org.springframework.core.convert.converter.Converter;

public class PaymentStatusConverter implements Converter<String, PaymentStatus> {
    @Override
    public PaymentStatus convert(String source) {
        return PaymentStatus.valueOf(source.toUpperCase());
    }
}

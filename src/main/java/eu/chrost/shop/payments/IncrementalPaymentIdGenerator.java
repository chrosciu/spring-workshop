package eu.chrost.shop.payments;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IncrementalPaymentIdGenerator implements PaymentIdGenerator {
    private static final String ID_FORMAT = "%010d";

    private long initialValue;
    private int step;

    @Override
    public String getNext() {
        initialValue += step;
        return String.format(ID_FORMAT, initialValue);
    }
}

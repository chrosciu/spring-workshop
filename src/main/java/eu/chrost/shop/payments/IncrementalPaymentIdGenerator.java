package eu.chrost.shop.payments;

import lombok.Setter;

public class IncrementalPaymentIdGenerator implements PaymentIdGenerator {

    private static final String ID_FORMAT = "%010d";

    @Setter
    private long index;
    @Setter
    private long step;

    @Override
    public String getNext() {
        index += step;
        return String.format(ID_FORMAT, index);
    }

}

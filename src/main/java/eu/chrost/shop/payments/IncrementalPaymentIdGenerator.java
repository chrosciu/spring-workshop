package eu.chrost.shop.payments;

import lombok.Setter;

//@Scope(BeanDefinition.SCOPE_PROTOTYPE)
//@Lazy
public class IncrementalPaymentIdGenerator implements PaymentIdGenerator {
    private static final String ID_FORMAT = "%010d";

    @Setter
    private long index;
    @Setter
    private long step;

    @Override
    public String getNext() {
        index = index + step;
        return String.format(ID_FORMAT, index);
    }

}

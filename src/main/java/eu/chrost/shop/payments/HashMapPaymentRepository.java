package eu.chrost.shop.payments;

import java.util.HashMap;
import java.util.Map;

public class HashMapPaymentRepository implements PaymentRepository {
    private final Map<String, Payment> payments = new HashMap<>();

    @Override
    public Payment save(Payment payment) {
        payments.put(payment.getId(), payment);
        return payment;
    }
}

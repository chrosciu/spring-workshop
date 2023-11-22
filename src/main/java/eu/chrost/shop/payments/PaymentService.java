package eu.chrost.shop.payments;

public interface PaymentService {
    Payment process(PaymentRequest paymentRequest);
}

package eu.chrost.shop;

import eu.chrost.shop.payments.FakePaymentService;
import eu.chrost.shop.payments.PaymentRequest;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class Shop {
    public static void main(String[] args) {
        var paymentService = new FakePaymentService();
        var paymentRequest = PaymentRequest.builder()
                .money(BigDecimal.valueOf(100))
                .build();
        var payment = paymentService.process(paymentRequest);
        log.info("{}", payment);
    }
}

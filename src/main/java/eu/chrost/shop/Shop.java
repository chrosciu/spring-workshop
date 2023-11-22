package eu.chrost.shop;

import eu.chrost.shop.payments.FakePaymentService;
import eu.chrost.shop.payments.IncrementalPaymentIdGenerator;
import eu.chrost.shop.payments.LoggingPaymentService;
import eu.chrost.shop.payments.PaymentRequest;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class Shop {
    public static void main(String[] args) {
        var paymentIdGenerator = new IncrementalPaymentIdGenerator();
        var fakePaymentService = new FakePaymentService(paymentIdGenerator);
        var paymentService = new LoggingPaymentService(fakePaymentService);
        var paymentRequest = PaymentRequest.builder()
                .money(BigDecimal.valueOf(100))
                .build();
        var payment = paymentService.process(paymentRequest);
        log.info("{}", payment);
    }
}

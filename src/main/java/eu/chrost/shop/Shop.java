package eu.chrost.shop;

import eu.chrost.shop.payments.PaymentRequest;
import eu.chrost.shop.payments.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

@Slf4j
public class Shop {
    public static void main(String[] args) {
        try (var applicationContext = new AnnotationConfigApplicationContext("eu.chrost.shop")) {
            var paymentService = applicationContext.getBean(PaymentService.class);
            var paymentRequest = PaymentRequest.builder()
                    .money(BigDecimal.valueOf(100))
                    .build();
            var payment = paymentService.process(paymentRequest);
            log.info("{}", payment);
        }
    }
}

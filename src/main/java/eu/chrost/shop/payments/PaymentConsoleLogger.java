package eu.chrost.shop.payments;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
@Slf4j
public class PaymentConsoleLogger {
    @Before(value = "@annotation(eu.chrost.shop.payments.LogPayments) && args(paymentRequest)")
    public void beforePayment(PaymentRequest paymentRequest) {
        log.info("New payment request: {}", paymentRequest);
    }

    @After("@annotation(eu.chrost.shop.payments.LogPayments)")
    public void afterPayment() {
        log.info("After payment");
    }

    @AfterThrowing(value = "@annotation(eu.chrost.shop.payments.LogPayments)", throwing = "exception")
    public void onException(Exception exception) {
        log.info("Payment exception: {}", exception.getClass().getSimpleName());
    }

    @AfterReturning(value = "@annotation(eu.chrost.shop.payments.LogPayments)", returning = "payment")
    public void log(Payment payment) {
        log.info("A new payment of {} has been initiated", payment);
    }
}

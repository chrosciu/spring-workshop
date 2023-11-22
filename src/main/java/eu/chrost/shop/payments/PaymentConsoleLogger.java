package eu.chrost.shop.payments;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Aspect
@Slf4j
public class PaymentConsoleLogger {
    private static final String LOG_FORMAT = "A new payment of {} has been initiated";

    @AfterReturning(value = "@annotation(LogPayments)", returning = "payment")
    public void log(Payment payment) {
        log.info(LOG_FORMAT, payment);
    }

    @Around(value = "@annotation(LogPayments) && args(paymentRequest)")
    public Object aroundPayment(ProceedingJoinPoint proceedingJoinPoint, PaymentRequest paymentRequest) throws Throwable {
        log.info("Around payment: {}", paymentRequest);
        if (paymentRequest.getMoney().compareTo(BigDecimal.valueOf(10)) < 0) {
            //throw new IllegalStateException("Biednych nie obslugujemy");
            return Payment.builder().status(PaymentStatus.FAILED).build();
        }
        return proceedingJoinPoint.proceed();
    }
}

package eu.chrost.shop.common;

import eu.chrost.shop.payments.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@Order(Integer.MAX_VALUE - 1)
class AroundAspectOne {
    @Around("@annotation(a)")
    public Object executeOne(ProceedingJoinPoint proceedingJoinPoint, AroundTestAnnotation a) throws Throwable {
        log.info("In around aspect 1");
        return proceedingJoinPoint.proceed();
    }
}


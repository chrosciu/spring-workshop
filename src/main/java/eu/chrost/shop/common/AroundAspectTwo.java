package eu.chrost.shop.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
@Order(Integer.MAX_VALUE - 2)
class AroundAspectTwo {
    @Around("@annotation(a)")
    public Object executeTwo(ProceedingJoinPoint proceedingJoinPoint, AroundTestAnnotation a) throws Throwable {
        log.info("In around aspect 2");
        return proceedingJoinPoint.proceed();
    }
}

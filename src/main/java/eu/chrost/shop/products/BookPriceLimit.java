package eu.chrost.shop.products;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = BookPriceLimitValidator.class)
@Documented
public @interface BookPriceLimit {
    String message() default "Expensive books are not allowed";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

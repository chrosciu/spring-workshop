package eu.chrost.shop.products;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class BookPriceLimitValidator implements ConstraintValidator<BookPriceLimit, ProductInputDto> {
    @Override
    public boolean isValid(ProductInputDto product, ConstraintValidatorContext context) {
        if (ProductType.BOOK.equals(product.getType())) {
            if (new BigDecimal(product.getPrice()).compareTo(BigDecimal.valueOf(1000)) > 0) {
                return false;
            }
        }
        return true;
    }
}

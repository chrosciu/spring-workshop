package eu.chrost.shop.orders;

import eu.chrost.shop.payments.Payment;
import eu.chrost.shop.products.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    private Long id;
    @NonNull
    private List<Product> products;
    private Payment payment;

    public BigDecimal getTotalPrice() {
        return products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

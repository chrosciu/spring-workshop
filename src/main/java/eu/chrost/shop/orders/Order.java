package eu.chrost.shop.orders;

import eu.chrost.shop.payments.Payment;
import eu.chrost.shop.products.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="orders")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    @ManyToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Product> products;
    @OneToOne
    @ToString.Exclude
    private Payment payment;

    public BigDecimal getTotalPrice() {
        return products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

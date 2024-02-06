package eu.chrost.shop.orders;

import eu.chrost.shop.payments.Payment;
import eu.chrost.shop.products.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "products")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToMany
    private Set<Product> products;
    @OneToOne
    private Payment payment;

    public Order(Set<Product> products) {
        this.products = Set.copyOf(products);
    }

    public Set<Product> getProducts() {
        return Set.copyOf(products);
    }

    public BigDecimal getTotalPrice() {
        return products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

package eu.chrost.shop.products;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private ProductType type;
}

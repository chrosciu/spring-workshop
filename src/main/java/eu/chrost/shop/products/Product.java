package eu.chrost.shop.products;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @GeneratedValue
    @Id
    private Long id;
    private String name;
    private String description;
    @Column(precision = 38)
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private ProductType type;
}

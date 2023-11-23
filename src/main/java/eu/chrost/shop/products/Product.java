package eu.chrost.shop.products;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class Product {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private ProductType type;
}

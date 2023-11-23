package eu.chrost.shop.products;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductOutputDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private ProductType type;
}

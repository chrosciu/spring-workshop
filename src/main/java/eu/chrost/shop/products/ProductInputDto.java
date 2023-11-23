package eu.chrost.shop.products;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductInputDto {
    private String name;
    private String description;
    private BigDecimal price;
    private ProductType type;
}

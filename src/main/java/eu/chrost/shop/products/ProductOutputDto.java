package eu.chrost.shop.products;

import lombok.Data;

@Data
public class ProductOutputDto {
    private Long id;
    private String name;
    private String description;
    private String price;
    private ProductType type;
}

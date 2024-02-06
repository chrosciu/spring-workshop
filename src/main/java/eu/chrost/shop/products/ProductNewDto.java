package eu.chrost.shop.products;

import lombok.Data;

@Data
public class ProductNewDto {
    private String name;
    private String description;
    private String price;
    private String type;
}

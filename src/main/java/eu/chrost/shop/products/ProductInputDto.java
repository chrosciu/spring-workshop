package eu.chrost.shop.products;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@BookPriceLimit
public class ProductInputDto {
    @NotEmpty
    private String name;
    @Length(min = 3, max = 255)
    private String description;
    private String price;
    private ProductType type;
}

package eu.chrost.shop.orders;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class OrderInputDto {
    @NotEmpty
    private List<Long> productsIds;
}

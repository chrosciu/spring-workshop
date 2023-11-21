package eu.chrost.shop.orders;

import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class OrderInputDto {
    @NotEmpty
    private List<Long> productsIds;
}

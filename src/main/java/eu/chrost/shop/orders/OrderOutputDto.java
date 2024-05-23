package eu.chrost.shop.orders;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderOutputDto {
    private Long id;
    private List<Long> productsIds;
}

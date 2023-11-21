package eu.chrost.shop.orders;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderOutputDto {
    private Long id;
    private List<Long> productsIds;
}

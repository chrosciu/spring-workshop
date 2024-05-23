package eu.chrost.shop.orders;

import eu.chrost.shop.products.Product;
import eu.chrost.shop.products.ProductService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class OrderMapper {
    private final ProductService productService;

    public Order fromInputDto(OrderInputDto orderDto) {
        List<Product> products = orderDto.getProductsIds().stream()
                .map(productService::getById)
                .collect(Collectors.toList());
        return new Order(products);
    }

    public OrderOutputDto toOutputDto(Order order) {
        List<Long> productsIds = order.getProducts().stream()
                .map(Product::getId)
                .toList();
        return new OrderOutputDto(order.getId(), productsIds);
    }
}

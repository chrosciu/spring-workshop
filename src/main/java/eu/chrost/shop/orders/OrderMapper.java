package eu.chrost.shop.orders;

import eu.chrost.shop.products.Product;
import eu.chrost.shop.products.ProductService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public Order fromInputDto(OrderInputDto orderDto) {
        List<Product> products = orderDto.getProductsIds().stream()
            .map(productService::getBy)
            .collect(Collectors.toList());
        return new Order(products);
    }

    public OrderOutputDto toOutputDto(Order order) {
        List<Long> productsIds = order.getProducts().stream()
            .map(Product::getId)
            .collect(Collectors.toList());
        return new OrderOutputDto(order.getId(), productsIds);
    }
}

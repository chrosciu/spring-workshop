package eu.chrost.shop.products;

public class ProductMapper {
    public ProductOutputDto toOutputDto(Product product) {
        return ProductOutputDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .type(product.getType())
                .build();
    }
}

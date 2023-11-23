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

    public Product fromInputDto(ProductInputDto inputDto) {
        return Product.builder()
                .name(inputDto.getName())
                .description(inputDto.getDescription())
                .price(inputDto.getPrice())
                .type(inputDto.getType())
                .build();
    }
}

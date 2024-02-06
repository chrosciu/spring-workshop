package eu.chrost.shop.products;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductShortDto toShortDto(Product product);

    ProductDto toDto(Product product);

    Product fromNewDto(ProductNewDto productNewDto);
}

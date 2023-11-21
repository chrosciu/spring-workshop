package eu.chrost.shop.products;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductOutputDto toOutputDto(Product product);
    @Mapping(target="id", ignore = true)
    Product fromInputDto(ProductInputDto productDto);
}

package eu.chrost.shop.products;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public List<ProductOutputDto> getAllProducts() {
        return productService.getAll().stream()
                .map(productMapper::toOutputDto)
                .toList();
    }
}

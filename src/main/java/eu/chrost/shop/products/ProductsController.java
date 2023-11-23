package eu.chrost.shop.products;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping
    public ProductOutputDto addProduct(@RequestBody ProductInputDto inputDto) {
        var newProduct = productMapper.fromInputDto(inputDto);
        var savedProduct = productService.add(newProduct);
        return productMapper.toOutputDto(savedProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductOutputDto> getProduct(@PathVariable long id) {
        return ResponseEntity.ok(productMapper.toOutputDto(productService.getById(id)));
    }
}

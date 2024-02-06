package eu.chrost.shop.products;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public List<ProductShortDto> getAllProducts() {
        return productService.getAll().stream()
                .map(productMapper::toShortDto)
                .toList();
    }

    @GetMapping("{id}")
    public ProductDto getProduct(@PathVariable long id) {
        return productMapper.toDto(productService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductNewDto productNewDto) {
        var productToAdd = productMapper.fromNewDto(productNewDto);
        var addedProduct = productService.add(productToAdd);
        var productDto = productMapper.toDto(addedProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto);
    }

}

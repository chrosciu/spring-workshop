package eu.chrost.shop.products;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//TODO: Do not use repository directly here - training purpose only !
//TODO:
@RequestMapping("api/productSearch")
@RestController
@RequiredArgsConstructor
public class ProductSearchController {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @GetMapping("/byName")
    public List<ProductOutputDto> byName(@RequestParam String name) {
        return productRepository.findByNameContaining(name).stream()
            .map(productMapper::toOutputDto)
            .collect(Collectors.toList());
    }

    @GetMapping("/byType")
    public List<ProductOutputDto> byType(@RequestParam ProductType type) {
        return productRepository.findByType(type).stream()
            .map(productMapper::toOutputDto)
            .collect(Collectors.toList());
    }

    @GetMapping("/byMaxPrice")
    public List<ProductOutputDto> byMaxPrice(@RequestParam long maxPrice) {
        return productRepository.findByMaxPrice(maxPrice).stream()
            .map(productMapper::toOutputDto)
            .collect(Collectors.toList());
    }

    @GetMapping("/byDescription")
    public Optional<ProductOutputDto> byDescription(@RequestParam String description) {
        return productRepository.findByDescription(description)
            .map(productMapper::toOutputDto);

    }

}

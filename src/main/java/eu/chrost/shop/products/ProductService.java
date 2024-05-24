package eu.chrost.shop.products;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @CacheEvict(value = "products", allEntries = true)
    public Product add(Product product) {
        return productRepository.save(product);
    }

    @Cacheable("products")
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Cacheable("product")
    public Product getById(long id) {
        return productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
    }
}

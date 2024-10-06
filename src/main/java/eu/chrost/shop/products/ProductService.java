package eu.chrost.shop.products;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional //(default propagation is REQUIRED)
    //@Transactional(propagation = Propagation.REQUIRES_NEW)
    public Product add(Product product) {
        if (ProductType.BOOK == product.getType()) {
            throw new RuntimeException("Book cannot be saved!");
        }
        return productRepository.save(product);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(long id) {
        log.info("getting product with id {}", id);
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
}

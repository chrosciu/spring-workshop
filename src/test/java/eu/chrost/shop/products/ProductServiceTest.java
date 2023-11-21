package eu.chrost.shop.products;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;

    private static final Product VIDEO_PRODUCT = Product.builder()
            .name("Spring masterclass")
            .description("Praktyczny kurs Spring framework")
            .type(ProductType.VIDEO)
            .price(BigDecimal.valueOf(1500))
            .build();

    private static final Product BOOK_PRODUCT = Product.builder()
            .name("Spring guide")
            .description("Praktyczne ćwiczenia do samodzielnego wykonania")
            .type(ProductType.BOOK)
            .price(BigDecimal.valueOf(200))
            .build();

    @Test
    void shouldReturnAllProducts() {
        //given
        when(productRepository.findAll()).thenReturn(List.of(VIDEO_PRODUCT, BOOK_PRODUCT));

        //when
        var products = productService.getAll();

        //then
        assertThat(products).containsExactly(VIDEO_PRODUCT, BOOK_PRODUCT);
    }

    @Test
    void shouldSaveProduct() {
        //when
        productService.add(VIDEO_PRODUCT);

        //then
        verify(productRepository).save(VIDEO_PRODUCT);
    }
}

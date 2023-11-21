package eu.chrost.shop.products;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;

    @TestConfiguration
    static class MapperConfiguration {
        @Bean
        public ProductMapper productMapper() {
            return Mappers.getMapper(ProductMapper.class);
        }
    }

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
    @SneakyThrows
    void shouldReturnAllProducts() {
        //given
        when(productService.getAll())
                .thenReturn(List.of(VIDEO_PRODUCT, BOOK_PRODUCT));

        //when / then
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Spring masterclass")))
                .andExpect(jsonPath("$[1].name", is("Spring guide")));

    }

    @Test
    @SneakyThrows
    void shouldReturnProductWithGivenId() {
        //given
        var videoProductId = 1L;
        when(productService.getBy(videoProductId))
                .thenReturn((VIDEO_PRODUCT));

        //when / then
        mockMvc.perform(get("/api/products/{id}", videoProductId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Spring masterclass")));

    }

    @Test
    @SneakyThrows
    void shouldReturnNotFoundStatusForNonExistingProduct() {
        //given
        var someProductId = 1L;
        when(productService.getBy(someProductId))
                .thenThrow(new ProductNotFoundException(someProductId));

        //when / then
        mockMvc.perform(get("/api/products/{id}", someProductId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.description", is("Brak produktu o id 1")));

    }

    @Test
    @SneakyThrows
    void shouldAllowToCreateProductGivenAsRequestBody() {
        var audioProductJson = "{\"name\": \"Spring do poduszki\"}";

        when(productService.add(any())).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0, Product.class));

        mockMvc.perform(post("/api/products")
                        .contentType("application/json")
                        .content(audioProductJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Spring do poduszki")));

    }
}

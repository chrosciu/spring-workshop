package eu.chrost.shop.products;

import org.junit.jupiter.api.Test;
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

@WebMvcTest(ProductsController.class)
class ProductsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public ProductMapper productMapper() {
            return new ProductMapper();
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
    void allProductsShouldBeReturned() throws Exception {

        //given
        when(productService.getAll())
                .thenReturn(List.of(VIDEO_PRODUCT, BOOK_PRODUCT));

        //when / then
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Spring masterclass")))
                .andExpect(jsonPath("$[1].name", is("Spring guide")));

    }

    @Test
    void newProductShouldBeCreatedAndReturned() throws Exception {

        //given
        var productJson = """
                {
                    "name": "Spring do poduszki"
                }
                """;
        when(productService.add(any()))
                .thenAnswer(onMock -> onMock.getArgument(0, Product.class));

        //given / then
        mockMvc.perform(post("/products")
                        .contentType("application/json")
                        .content(productJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Spring do poduszki")));

    }

    @Test
    void existingProductShouldBeReturned() throws Exception {
        //given
        var someExistingProductId = 1L;
        when(productService.getById(someExistingProductId)).thenReturn(VIDEO_PRODUCT);

        //when / then
        mockMvc.perform(get("/products/{id}", someExistingProductId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Spring masterclass")));
    }

    @Test
    void nonExistingProductShouldEndWithUnprocessableEntity() throws Exception {
        //given
        var someNonExistingProductId = 1L;
        when(productService.getById(someNonExistingProductId)).thenThrow(new ProductNotFoundException());

        //when / then
        mockMvc.perform(get("/products/{id}", someNonExistingProductId))
                .andExpect(status().isUnprocessableEntity());
    }
}

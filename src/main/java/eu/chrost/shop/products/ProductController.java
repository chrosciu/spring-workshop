package eu.chrost.shop.products;

import eu.chrost.shop.common.web.ExceptionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("api/products")
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @Operation(summary = "List all products in shop")
    @ApiResponse(responseCode = "200", description = "List of products",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ProductOutputDto.class))))
    @GetMapping
    public ResponseEntity<List<ProductOutputDto>> getProducts() {
        List<Product> products = productService.getAll();
        return ResponseEntity.ok(products.stream()
            .map(productMapper::toOutputDto)
            .collect(Collectors.toList()));
    }

    @Operation(summary = "Add new product",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Prodcut to be added",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductOutputDto.class))))
    @ApiResponse(responseCode = "200", description = "Created product",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductOutputDto.class)))
    @ApiResponse(responseCode = "400", description = "Invalid input data",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDto.class)))
    @PostMapping
    public ResponseEntity<ProductOutputDto> addProduct(@Valid @RequestBody ProductInputDto productInputDto) {
        Product productToAdd = productMapper.fromInputDto(productInputDto);
        Product product = productService.add(productToAdd);
        return ResponseEntity.status(HttpStatus.CREATED).body(productMapper.toOutputDto(product));
    }

    @Operation(summary = "Show product with given id",
            parameters = {@Parameter(name = "id", description = "Id of the product", required = true,
                    in = ParameterIn.PATH, schema = @Schema(implementation = Long.class))})
    @ApiResponse(responseCode = "200", description = "Single product",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductOutputDto.class)))
    @ApiResponse(responseCode = "404", description = "Product not found")
    @GetMapping("{id}")
    public ResponseEntity<ProductOutputDto> getProduct(@PathVariable Long id) {
        Product product = productService.getBy(id);
        return ResponseEntity.ok(productMapper.toOutputDto(product));
    }
}

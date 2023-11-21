package eu.chrost.shop.products;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductMvcController {
    private final ProductService productService;
    private final ProductMapper productMapper;
    @GetMapping
    public String showProducts(Model model) {
        List<ProductOutputDto> products = productService.getAll().stream()
                .map(productMapper::toOutputDto)
                .collect(Collectors.toList());
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new ProductInputDto());
        return "new-product";
    }

    @PostMapping
    public String addProduct(@Valid @ModelAttribute("product") ProductInputDto product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new-product";
        }
        Product productToAdd = productMapper.fromInputDto(product);
        productService.add(productToAdd);
        return "redirect:/products";
    }
}

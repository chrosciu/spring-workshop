package eu.chrost.shop.orders;

import eu.chrost.shop.products.ProductMapper;
import eu.chrost.shop.products.ProductOutputDto;
import eu.chrost.shop.products.ProductService;
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
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderMvcController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public String showOrders(Model model) {
        List<OrderOutputDto> orders = orderService.getAll().stream()
                .map(orderMapper::toOutputDto)
                .collect(Collectors.toList());
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/new")
    public String newOrder(Model model) {
        model.addAttribute("order", new OrderInputDto());
        return "new-order";
    }

    @PostMapping
    public String addOrder(@Valid @ModelAttribute("order") OrderInputDto orderInputDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new-order";
        }
        Order orderToAdd = orderMapper.fromInputDto(orderInputDto);
        orderService.add(orderToAdd);
        return "redirect:/orders";
    }

    @ModelAttribute("products")
    public List<ProductOutputDto> getProducts() {
        return productService.getAll().stream()
                .map(productMapper::toOutputDto)
                .collect(Collectors.toList());
    }

}

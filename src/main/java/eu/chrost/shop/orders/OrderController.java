package eu.chrost.shop.orders;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/orders")
@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping
    public ResponseEntity<OrderOutputDto> addOrder(@RequestBody OrderInputDto orderDto) {
        Order orderToAdd = orderMapper.fromInputDto(orderDto);
        Order order = orderService.add(orderToAdd);
        return ResponseEntity.ok(orderMapper.toOutputDto(order));
    }

    @GetMapping
    public ResponseEntity<OrderOutputDto> getOrder(@RequestParam Long id) {
        Order order = orderService.getBy(id);
        return ResponseEntity.ok(orderMapper.toOutputDto(order));
    }
}

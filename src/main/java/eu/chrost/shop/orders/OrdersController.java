package eu.chrost.shop.orders;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/orders")
@RestController
@RequiredArgsConstructor
public class OrdersController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping
    public ResponseEntity<OrderOutputDto> addOrder(@RequestBody OrderInputDto orderDto) {
        Order orderToAdd = orderMapper.fromInputDto(orderDto);
        Order order = orderService.add(orderToAdd);
        return ResponseEntity.ok(orderMapper.toOutputDto(order));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderOutputDto> getOrder(@PathVariable long id) {
        Order order = orderService.getBy(id);
        return ResponseEntity.ok(orderMapper.toOutputDto(order));
    }
}

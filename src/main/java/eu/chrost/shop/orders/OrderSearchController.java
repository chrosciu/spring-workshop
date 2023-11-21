package eu.chrost.shop.orders;

import eu.chrost.shop.payments.PaymentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("api/orderSearch")
@RestController
@RequiredArgsConstructor
public class OrderSearchController {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @GetMapping("/byPaymentStatus")
    public List<OrderOutputDto> byPaymentStatus(@RequestParam PaymentStatus paymentStatus) {
        return orderRepository.findByPaymentStatus(paymentStatus).stream()
                .map(orderMapper::toOutputDto)
                .collect(Collectors.toList());
    }
}

package eu.chrost.shop;

import eu.chrost.shop.orders.Order;
import eu.chrost.shop.orders.OrderService;
import eu.chrost.shop.payments.Payment;
import eu.chrost.shop.payments.PaymentRequest;
import eu.chrost.shop.payments.PaymentService;
import eu.chrost.shop.products.Product;
import eu.chrost.shop.products.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
public class ShopService {
    private final OrderService orderService;
    private final PaymentService paymentService;
    private final ProductService productService;

    public Product addProduct(Product product) {
        return productService.add(product);
    }

    public List<Product> getProducts() {
        return productService.getAll();
    }

    public Order placeOrder(Order order) {
        return orderService.add(order);
    }

    @Transactional
    public Payment payForOrder(long orderId) {
        var order = orderService.getBy(orderId);
        var paymentRequest = PaymentRequest.builder()
                .money(order.getTotalPrice())
                .build();
        var payment = paymentService.process(paymentRequest);
        order.setPayment(payment);
        orderService.update(order);
        return payment;
    }
}


package demo.fls.eshop.orders;

import demo.fls.eshop.auth.AuthUtil;
import demo.fls.eshop.cart.CartsService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.UUID;

@Component
@Validated
public class OrdersService {

    private final OrderRepository orderRepository;
    private final CartsService cartsService;

    public OrdersService(OrderRepository orderRepository, CartsService cartsService) {
        this.orderRepository = orderRepository;
        this.cartsService = cartsService;
    }

    public Collection<ProductOrder> orders(Authentication authentication) {
        UUID profileId = AuthUtil.getCurrentUserId(authentication);
        return orderRepository.findByProfileId(profileId);
    }

    public ProductOrder makeOrder(@Valid ProductOrder newProductOrder, Authentication authentication) {
        UUID profileId = newProductOrder.profileId(); // A01:2021 – Broken Access Control. Fix: newProductOrder = AuthUtil.getCurrentUserId(authentication);
        ProductOrder savedProductOrder = orderRepository.save(
                new ProductOrder(
                        null,
                        profileId,
                        newProductOrder.productOrderItems(),
                        newProductOrder.creditCardNumber(), // A02:2021 – Cryptographic Failures. Fix: store only last four digits of credit card number just for history
                        newProductOrder.shippingAddress()
                )
        );
        cartsService.removeCurrentCart(authentication);
        return savedProductOrder;
    }
}
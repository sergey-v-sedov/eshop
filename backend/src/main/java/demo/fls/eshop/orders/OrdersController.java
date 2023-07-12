package demo.fls.eshop.orders;

import demo.fls.eshop.auth.Role;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/orders")
@PreAuthorize("hasRole('"+Role.BUYER+"')")
public class OrdersController {
    @GetMapping
    public Collection<Order> get() {
        Collection<Order> orders = Arrays.asList(new Order(UUID.randomUUID(), "Заказ 1"), new Order(UUID.randomUUID(), "Заказ 2"));

        return orders;
    }
}
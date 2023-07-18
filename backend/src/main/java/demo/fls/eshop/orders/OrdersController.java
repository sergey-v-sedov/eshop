package demo.fls.eshop.orders;

import demo.fls.eshop.auth.Role;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/v1/orders", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@PreAuthorize("hasRole('"+Role.BUYER+"')")
public class OrdersController {
    private final OrdersService ordersService;
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }
    @GetMapping
    public Collection<ProductOrder> get(Authentication authentication) {
        return ordersService.orders(authentication);
    }

    @PutMapping
    public ProductOrder put(@RequestBody ProductOrder newProductOrder, Authentication authentication) {
        return ordersService.makeOrder(newProductOrder, authentication);
    }
}
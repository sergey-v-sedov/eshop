package demo.fls.eshop.cart;

import demo.fls.eshop.auth.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/carts", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@PreAuthorize("hasRole('"+ Role.BUYER+"')")
public class CartsController {
    private final CartsService cartsService;
    public CartsController(CartsService cartsService) {
        this.cartsService = cartsService;
    }
    @GetMapping
    public Collection<CartItem> getCartItems(Authentication authentication) {
        return cartsService.getCurrentUserCartItems(authentication);
    }

    @GetMapping(path = "/items/{productId}")
    public CartItem getCartItem(UUID productId, Authentication authentication) {
        return cartsService.getCartItem(productId, authentication);
    }

    @PutMapping(path = "/items/{productId}")
    @ResponseStatus( HttpStatus.CREATED )
    public void addCartItem(@PathVariable UUID productId, Authentication authentication) {
        cartsService.addCartItem(productId, authentication);

    }

    @DeleteMapping(path = "/items/{productId}")
    @ResponseStatus( HttpStatus.CREATED )
    public void removeCartItem(@PathVariable UUID productId, Authentication authentication) {
        cartsService.removeCartItem(productId, authentication);
    }
}
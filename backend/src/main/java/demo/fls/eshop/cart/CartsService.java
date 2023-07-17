package demo.fls.eshop.cart;

import demo.fls.eshop.auth.AuthUtil;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Component
public class CartsService {
    public final CartRepository cartRepository;
    public final CartItemRepository cartItemRepository;
    public CartsService(CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public Collection<CartItem> getCurrentUserCartItems(Authentication authentication) {
        Cart cart = getCartByCurrentBuyer(authentication);
        Collection<CartItem> cartItems = cartItemRepository.findByCartId(cart.id());

        return cartItems;
    }

    public CartItem getCartItem(UUID productId, Authentication authentication) {
        Cart cart = getCartByCurrentBuyer(authentication);
        return cartItemRepository.findByCartIdAndProductId(cart.id(), productId).orElseThrow(() -> new IllegalArgumentException("CartItem not found"));
    }

    public void addCartItem(UUID productId, Authentication authentication) {
        Cart cart = getCartByCurrentBuyer(authentication);
        cartItemRepository.findByCartIdAndProductId(cart.id(), productId).orElseGet(() -> cartItemRepository.save(new CartItem(null, cart.id(), productId)));
    }

    public void removeCartItem(UUID productId, Authentication authentication) {
        Cart cart = getCartByCurrentBuyer(authentication);
        cartItemRepository.findByCartIdAndProductId(cart.id(), productId).ifPresent(cartItemRepository::delete);
    }

    private Cart getCartByCurrentBuyer(Authentication authentication) {
        UUID profileId = AuthUtil.getCurrentUserId(authentication);
        return cartRepository.findByProfileId(profileId).orElseGet(() -> cartRepository.save(new Cart(null, profileId)));
    }
}
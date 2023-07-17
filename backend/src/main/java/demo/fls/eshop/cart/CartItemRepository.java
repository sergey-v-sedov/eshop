package demo.fls.eshop.cart;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem, UUID> {
    Collection<CartItem> findByCartId(UUID cartId);

    Optional<CartItem> findByCartIdAndProductId(UUID cartId, UUID productId);
}
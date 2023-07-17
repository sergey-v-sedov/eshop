package demo.fls.eshop.cart;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartRepository extends CrudRepository<Cart, UUID> {
    Optional<Cart> findByProfileId(UUID profileId);
}
package demo.fls.eshop.cart;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

import java.util.UUID;

public record CartItem(@Id UUID id, @NotNull UUID cartId, @NotNull UUID productId) {}
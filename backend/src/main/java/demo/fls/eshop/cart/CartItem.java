package demo.fls.eshop.cart;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

import java.util.UUID;

public record CartItem(@JsonIgnore @Id UUID id, @JsonIgnore @NotNull UUID cartId, @NotNull UUID productId) {}
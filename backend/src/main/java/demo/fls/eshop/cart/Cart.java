package demo.fls.eshop.cart;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

import java.util.UUID;

public record Cart(@Id UUID id, @NotNull UUID profileId) {}
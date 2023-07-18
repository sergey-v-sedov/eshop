package demo.fls.eshop.orders;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public record ProductOrderItem(@Id UUID id, UUID productOrderId, UUID productId) {}
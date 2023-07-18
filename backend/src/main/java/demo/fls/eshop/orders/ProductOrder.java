package demo.fls.eshop.orders;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.Set;
import java.util.UUID;

public record ProductOrder(@Id UUID id, UUID profileId, @MappedCollection(idColumn = "product_order_id") Set<ProductOrderItem> productOrderItems, String creditCardNumber, String shippingAddress) {}
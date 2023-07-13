package demo.fls.eshop.storefront;

import java.net.URI;
import java.util.UUID;

public record Product (UUID id, String title, String description, URI image, Double price) {}
package demo.fls.eshop.storefront;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/products")
public class StorefrontController {
    @GetMapping
    public Collection<Product> get() {
        Collection<Product> products = Arrays.asList(
                new Product(UUID.randomUUID(), "Товар 1"),
                new Product(UUID.randomUUID(), "Товар 2"),
                new Product(UUID.randomUUID(), "Товар 3"),
                new Product(UUID.randomUUID(), "Товар 4"),
                new Product(UUID.randomUUID(), "Товар 5"),
                new Product(UUID.randomUUID(), "Товар 6"),
                new Product(UUID.randomUUID(), "Товар 7"),
                new Product(UUID.randomUUID(), "Товар 8"),
                new Product(UUID.randomUUID(), "Товар 9"),
                new Product(UUID.randomUUID(), "Товар 10")
        );

        return products;
    }
}

package demo.fls.eshop.integration;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import demo.fls.eshop.registrations.Registration;
import demo.fls.eshop.storefront.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests extends IntegrationTestsInfrastructureInitializer{

    private HttpHeaders headers;

    @BeforeAll
    public void beforeAll() {
        registerSuccess();
    }

    void registerSuccess() {
        ResponseEntity response = testRestTemplate.exchange("/api/v1/registrations",
                HttpMethod.POST,
                new HttpEntity<>(new Registration(null, "new user", "user@user", "pass"), headers),
                new ParameterizedTypeReference<>(){});

        assertThat(response.getHeaders().get("Location").get(0)).contains("/profile/");
    }

    @Test
    void registerValidationError() {
        ResponseEntity response = testRestTemplate.exchange("/api/v1/registrations",
                HttpMethod.POST,
                new HttpEntity<>(new Registration(null, "new user", "invalid(at).email", "pass"), headers),
                new ParameterizedTypeReference<>(){});

        assertThat(response.getStatusCode().is4xxClientError());
    }

    @BeforeEach
    public void beforeEach() {
        auth();
    }

    void auth() {
        String tokenResponse = testRestTemplate.withBasicAuth("user@user", "pass").postForObject("/api/v1/auth", null, String.class);

        JsonObject jsonResponse = new JsonParser().parse(tokenResponse).getAsJsonObject();

        assertThat(jsonResponse.isJsonObject());
        assertThat(jsonResponse.get("jwt").getAsString().isEmpty());

        String token = jsonResponse.get("jwt").getAsString();

        headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
    }

    @Test
    void find() {
        ResponseEntity<Collection<Product>> response = testRestTemplate.exchange("/api/v1/products",
                HttpMethod.GET,
                new HttpEntity<>(null, headers),
                new ParameterizedTypeReference<>(){});

        assertThat(response.getBody()).hasSize(10);
    }
}
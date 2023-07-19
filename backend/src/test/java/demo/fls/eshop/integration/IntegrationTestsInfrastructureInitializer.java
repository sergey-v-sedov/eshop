package demo.fls.eshop.integration;

import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
@ContextConfiguration(initializers = IntegrationTestsInfrastructureInitializer.TestPropertyInitializer.class)
public abstract class IntegrationTestsInfrastructureInitializer {

    @Autowired
    public TestRestTemplate testRestTemplate;

    static class TestPropertyInitializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            PostgreSQLContainer<?> POSTGRESQL_CONTAINER =
                    new PostgreSQLContainer<>("postgres:15.2-alpine").withInitScript("db.sql");
            POSTGRESQL_CONTAINER.start();

            TestPropertyValues values = TestPropertyValues.of(
                    "spring.datasource.url=" + POSTGRESQL_CONTAINER.getJdbcUrl(),
                    "spring.datasource.username=" + POSTGRESQL_CONTAINER.getUsername(),
                    "spring.datasource.password=" + POSTGRESQL_CONTAINER.getPassword(),
                    "server.ssl.enabled=false");
            values.applyTo(applicationContext);
        }
    }
}
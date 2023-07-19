package demo.fls.eshop.config;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(
        name = "server.http.enabled",
        matchIfMissing = true) // A02:2021 Cryptographic Failures. HTTP enabled by default
public class HttpConfig implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {
    @Value("${server.http.port}")
    private int httpPort;

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setPort(httpPort);
        connector.setSecure(false);
        factory.addAdditionalTomcatConnectors(connector);
    }
}
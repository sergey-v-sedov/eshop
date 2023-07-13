package demo.fls.eshop.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.UUID;

public class AuthUtil {
    public static UUID getCurrentUserId(Authentication authentication) {
        Jwt jwt = (Jwt)authentication.getPrincipal();
        return UUID.fromString(jwt.getId());
    }
}
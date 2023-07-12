package demo.fls.eshop.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.GrantedAuthority;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final JwtEncoder encoder;
    public AuthController(JwtEncoder encoder) {
        this.encoder = encoder;
    }
    @PostMapping("")
    public String auth(Authentication authentication) {
        return "{\"jwt\":\"" + buildJwt(authentication) + "\"}";
    }

    public String buildJwt(Authentication authentication) {
        Instant now = Instant.now();
        long expiry = 36000L;
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
package demo.fls.eshop.auth;

import demo.fls.eshop.registrations.Profile;
import demo.fls.eshop.registrations.ProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
public class AuthController {
    private final JwtEncoder encoder;
    private final ProfileRepository profileRepository;
    public AuthController(JwtEncoder encoder, ProfileRepository profileRepository) {
        this.encoder = encoder;
        this.profileRepository = profileRepository;
    }
    @PostMapping("")
    public String auth(Authentication authentication) {
        Optional<Profile> userProfile = profileRepository.findFirstByEmail(authentication.getName());
        if(userProfile.isEmpty()) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Profile not found");

        return "{\"jwt\":\"" + buildJwt(authentication, userProfile.get()) + "\"}";
    }

    public String buildJwt(Authentication authentication, Profile profile) {
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
                .id(profile.getId().toString())
                .claim("scope", scope)
                .build();
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
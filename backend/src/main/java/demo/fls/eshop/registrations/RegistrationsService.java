package demo.fls.eshop.registrations;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;
import java.util.UUID;

@Component
@Validated
public class RegistrationsService {
    private final Logger logger = LoggerFactory.getLogger(RegistrationsService.class);
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;


    public RegistrationsService(ProfileRepository profileRepository, PasswordEncoder passwordEncoder) {
        this.profileRepository = profileRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public UUID registration(@Valid Registration registration) {

        Optional<Profile> existedProfile = profileRepository.findFirstByEmail(registration.email());
        if(existedProfile.isPresent()) throw new IllegalArgumentException("Profile with provided email already exists");

        UUID userId = UUID.randomUUID();

        Profile profile = new Profile();
        profile.setUsername(registration.username());
        profile.setEmail(registration.email());
        profile.setPassword(passwordEncoder.encode(registration.password()));
        profileRepository.save(profile);

        return userId;
    }
}
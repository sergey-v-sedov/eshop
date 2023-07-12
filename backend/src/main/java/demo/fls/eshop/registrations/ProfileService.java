package demo.fls.eshop.registrations;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Component
@Validated
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;

    public ProfileService(ProfileRepository profileRepository, PasswordEncoder passwordEncoder) {
        this.profileRepository = profileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void update(@Valid Profile profile) {
        Optional<Profile> currentProfile = this.profileRepository.findById(profile.getId()); //Check access vulnerability (need check current session id)

        if(currentProfile.isPresent()) {
            profile.setPassword(passwordEncoder.encode(profile.getPassword() == null ? currentProfile.get().getPassword() : profile.getPassword()));
            this.profileRepository.save(profile);
        } else {
            throw new IllegalArgumentException("Profile not found");
        }
    }
}
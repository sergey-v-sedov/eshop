package demo.fls.eshop.registrations;

import demo.fls.eshop.auth.AuthUtil;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Component
@Validated
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;

    public ProfileService(ProfileRepository profileRepository, PasswordEncoder passwordEncoder) {
        this.profileRepository = profileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void update(@Valid Profile profile, Authentication authentication) throws IllegalAccessException {
        Optional<Profile> currentProfile = this.profileRepository.findById(AuthUtil.getCurrentUserId(authentication));
        if(currentProfile.isEmpty()) throw new IllegalArgumentException("Profile not found");

        if(profile.getId().equals(currentProfile.get().getId())) {
            profile.setPassword(passwordEncoder.encode(profile.getPassword() == null ? currentProfile.get().getPassword() : profile.getPassword()));
            this.profileRepository.save(profile);
        } else {
            throw new IllegalAccessException("Profile access forbidden");
        }
    }
}
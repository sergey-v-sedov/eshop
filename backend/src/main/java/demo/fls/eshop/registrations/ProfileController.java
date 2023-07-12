package demo.fls.eshop.registrations;

import demo.fls.eshop.auth.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api/v1/profiles", consumes = MediaType.APPLICATION_JSON_VALUE)
@PreAuthorize("hasRole('"+ Role.BUYER+"')")
public class ProfileController {

    private final ProfileRepository profileRepository;
    private final ProfileService profileService;

    public ProfileController(ProfileRepository profileRepository, ProfileService profileService) {
        this.profileRepository = profileRepository;
        this.profileService = profileService;
    }

    @GetMapping("/my")
    public Profile get(Authentication authentication) {
        return this.profileRepository.findFirstByEmail(authentication.getName()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found"));
    }

    @PutMapping("/my")
    public void put(@RequestBody Profile profile) {
        this.profileService.update(profile);
    }
}
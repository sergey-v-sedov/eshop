package demo.fls.eshop.registrations;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/registrations", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class RegistrationsController {
    private RegistrationsService registrationsService;


    public RegistrationsController(RegistrationsService registrationsService) {
        this.registrationsService = registrationsService;
    }

    @PostMapping
    public ResponseEntity post(@RequestBody Registration registration) throws URISyntaxException {
        try {
            UUID userId = this.registrationsService.registration(registration);
            return ResponseEntity.created(new URI("/profile/"+userId)).build();
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }
}
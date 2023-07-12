package demo.fls.eshop.registrations;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public record Registration (UUID id, @NotBlank String username, @NotEmpty @Email String email, @NotBlank String password) {}
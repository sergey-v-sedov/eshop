package demo.fls.eshop.registrations;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record Registration (UUID id, @NotBlank String username, @NotBlank @Email String email, @NotBlank String password) {}
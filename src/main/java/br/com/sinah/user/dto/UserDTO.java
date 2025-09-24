package br.com.sinah.user.dto;

import java.time.Instant;
import java.util.UUID;

public record UserDTO(
        UUID uuid,
        String username,
        String firstName,
        String lastName,
        String email,
        String role,
        Instant createdAt,
        Instant updatedAt
) {
}
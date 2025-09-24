package br.com.sinah.auth.dto;

import java.time.Instant;

public record LoginResponseDTO(
        String accessToken,
        String refreshToken,
        Instant expiresAt
) {
}

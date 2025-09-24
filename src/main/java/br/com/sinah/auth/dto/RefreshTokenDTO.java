package br.com.sinah.auth.dto;

import java.time.Instant;
import java.util.UUID;

public record RefreshTokenDTO(UUID uuid, String token, Instant issuedAt, Instant expiresAt) {
}

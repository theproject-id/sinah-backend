package br.com.sinah.auth.dto;

import java.time.Instant;

public record RefreshTokenResponseDTO(String accessToken, String refreshToken, Instant expiresIn) {}

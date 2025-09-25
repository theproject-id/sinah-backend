package br.com.sinah.auth.service;

import br.com.sinah.auth.dto.RefreshTokenDTO;
import br.com.sinah.auth.mapper.RefreshTokenMapper;
import br.com.sinah.auth.model.RefreshTokenModel;
import br.com.sinah.auth.repository.RefreshTokenRepository;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public RefreshTokenDTO generateToken(UUID userUUID) {
        var uuid = UUID.randomUUID();
        var expiresAt = Instant.now().plus(7, ChronoUnit.DAYS);
        var randomBytes = KeyGenerators.secureRandom(32).generateKey();
        var token = Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);

        var refreshTokenModel = new RefreshTokenModel(uuid, token, userUUID, Instant.now(), expiresAt);
        var savedRefreshToken = refreshTokenRepository.save(refreshTokenModel);
        return RefreshTokenMapper.toDTO(savedRefreshToken);
    }
}

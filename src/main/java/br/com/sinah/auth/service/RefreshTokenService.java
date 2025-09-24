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
        UUID uuid = UUID.randomUUID();
        Instant expiresAt = Instant.now().plus(7, ChronoUnit.DAYS);
        byte[] randomBytes = KeyGenerators.secureRandom(32).generateKey();
        String token = Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);

        var refreshToken = new RefreshTokenModel();
        refreshToken.setUuid(uuid);
        refreshToken.setToken(token);
        refreshToken.setIssuedAt(Instant.now());
        refreshToken.setExpiresAt(expiresAt);
        refreshToken.setUserUuid(userUUID);
        var savedRefreshToken = refreshTokenRepository.save(refreshToken);

        return RefreshTokenMapper.toDTO(savedRefreshToken);
    }
}

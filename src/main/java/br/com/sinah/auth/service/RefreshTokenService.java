package br.com.sinah.auth.service;

import br.com.sinah.auth.dto.RefreshTokenDTO;
import br.com.sinah.auth.dto.RefreshTokenResponseDTO;
import br.com.sinah.auth.mapper.RefreshTokenMapper;
import br.com.sinah.auth.model.RefreshTokenModel;
import br.com.sinah.auth.repository.RefreshTokenRepository;
import br.com.sinah.common.exception.NotFoundException;
import br.com.sinah.common.exception.UnauthorizedException;
import br.com.sinah.common.jwt.JwtManager;
import br.com.sinah.user.service.UserService;

import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final JwtManager jwtManager;

    private final UserService userService;

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(
            JwtManager jwtManager, UserService userService, RefreshTokenRepository refreshTokenRepository) {
        this.jwtManager = jwtManager;
        this.userService = userService;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Transactional
    public RefreshTokenDTO generateToken(UUID userUUID) {
        var uuid = UUID.randomUUID();
        var expiresAt = Instant.now().plus(7, ChronoUnit.DAYS);
        var randomBytes = KeyGenerators.secureRandom(32).generateKey();
        var token = Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);

        var refreshTokenModel = new RefreshTokenModel(uuid, token, userUUID, Instant.now(), expiresAt);
        var savedRefreshToken = refreshTokenRepository.save(refreshTokenModel);
        return RefreshTokenMapper.toDTO(savedRefreshToken);
    }

    @Transactional
    public RefreshTokenResponseDTO refreshToken(String token) {
        var refresh = refreshTokenRepository
                .findByToken(token)
                .orElseThrow(() -> new NotFoundException("Invalid refresh token"));

        if (refresh.isRevoked() || refresh.getExpiresAt().isBefore(Instant.now())) {
            throw new UnauthorizedException("Expired or revoked refresh token");
        }
        refresh.setRevoked(true);
        refreshTokenRepository.save(refresh);

        var user = userService.loadUserByUUID(refresh.getUserUuid());
        RefreshTokenDTO refreshToken = generateToken(user.getUuid());

        String accessToken = jwtManager.generateToken(user, refreshToken.uuid());
        return new RefreshTokenResponseDTO(accessToken, refreshToken.token(), refreshToken.expiresAt());
    }
}

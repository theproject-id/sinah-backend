package br.com.sinah.auth.service;

import br.com.sinah.auth.dto.LoginRequestDTO;
import br.com.sinah.auth.dto.LoginResponseDTO;
import br.com.sinah.auth.dto.RefreshTokenDTO;
import br.com.sinah.common.jwt.JwtManager;
import br.com.sinah.user.model.UserModel;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtManager jwtManager;

    private final RefreshTokenService refreshTokenService;

    private final AuthenticationManager authenticationManager;

    public AuthService(
            JwtManager jwtManager,
            RefreshTokenService refreshTokenService,
            AuthenticationManager authenticationManager
    ) {
        this.jwtManager = jwtManager;
        this.refreshTokenService = refreshTokenService;
        this.authenticationManager = authenticationManager;
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(loginRequestDTO.email(), loginRequestDTO.password());

        Authentication authentication = authenticationManager.authenticate(authToken);
        UserModel user = (UserModel) authentication.getPrincipal();

        RefreshTokenDTO refreshToken = refreshTokenService.generateToken(user.getUuid());
        String jwt = jwtManager.generateToken(user, refreshToken.uuid());

        return new LoginResponseDTO(jwt, refreshToken.token(), refreshToken.expiresAt());
    }
}

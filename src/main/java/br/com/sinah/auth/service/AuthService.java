package br.com.sinah.auth.service;

import br.com.sinah.auth.dto.LoginRequestDTO;
import br.com.sinah.auth.dto.LoginResponseDTO;
import br.com.sinah.common.jwt.JwtManager;
import br.com.sinah.user.model.UserModel;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
        var authToken = new UsernamePasswordAuthenticationToken(loginRequestDTO.email(), loginRequestDTO.password());
        var authentication = authenticationManager.authenticate(authToken);
        var user = (UserModel) authentication.getPrincipal();

        var refreshToken = refreshTokenService.generateToken(user.getUuid());
        var jwt = jwtManager.generateToken(user, refreshToken.uuid());
        return new LoginResponseDTO(jwt, refreshToken.token(), refreshToken.expiresAt());
    }
}

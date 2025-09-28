package br.com.sinah.auth.service;

import br.com.sinah.auth.dto.LoginRequestDTO;
import br.com.sinah.auth.dto.LoginResponseDTO;
import br.com.sinah.common.jwt.JwtManager;
import br.com.sinah.user.dto.UserDTO;
import br.com.sinah.user.mapper.UserMapper;
import br.com.sinah.user.model.UserModel;
import br.com.sinah.user.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    private final JwtManager jwtManager;

    private final UserService userService;

    private final RefreshTokenService refreshTokenService;

    private final AuthenticationManager authenticationManager;

    public AuthService(
            JwtManager jwtManager,
            UserService userService,
            RefreshTokenService refreshTokenService,
            AuthenticationManager authenticationManager
    ) {
        this.jwtManager = jwtManager;
        this.userService = userService;
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

    public UserDTO authenticate() {
        var authenticationToken = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        var token = authenticationToken.getToken();
        var user = userService.loadUserByUUID(UUID.fromString(token.getSubject()));
        return UserMapper.toDTO(user);
    }
}

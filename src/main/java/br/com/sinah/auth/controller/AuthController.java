package br.com.sinah.auth.controller;

import br.com.sinah.auth.dto.LoginRequestDTO;
import br.com.sinah.auth.dto.LoginResponseDTO;
import br.com.sinah.auth.dto.RefreshTokenResponseDTO;
import br.com.sinah.auth.service.AuthService;
import br.com.sinah.auth.service.RefreshTokenService;
import br.com.sinah.user.dto.UserDTO;

import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    private final RefreshTokenService refreshTokenService;

    public AuthController(AuthService authService, RefreshTokenService refreshTokenService) {
        this.authService = authService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping(
            value = "/login",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        var response = authService.login(loginRequestDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/refresh-token", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RefreshTokenResponseDTO> refresh(@RequestHeader("X-Refresh-Token") String refreshToken) {
        var response = refreshTokenService.refreshToken(refreshToken);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> me() {
        var response = authService.authenticate();
        return ResponseEntity.ok(response);
    }
}

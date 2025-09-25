package br.com.sinah.auth.controller;

import br.com.sinah.auth.dto.LoginRequestDTO;
import br.com.sinah.auth.dto.LoginResponseDTO;
import br.com.sinah.auth.service.AuthService;
import br.com.sinah.user.dto.UserDTO;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(
            value = "/login",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(authService.login(loginRequestDTO));
    }

    @GetMapping(
            value = "/me",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserDTO> me() {
        return ResponseEntity.ok(authService.authenticate());
    }
}

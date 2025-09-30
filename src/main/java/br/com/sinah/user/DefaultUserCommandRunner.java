package br.com.sinah.user;

import br.com.sinah.user.model.UserModel;
import br.com.sinah.user.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DefaultUserCommandRunner implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DefaultUserCommandRunner(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        String defaultUsername = "default1.user";
        UUID defaultUuid = UUID.fromString("00000000-0000-0000-0000-000000000001");
        UserModel user = new UserModel();
        user.setUuid(defaultUuid);
        user.setFirstName("Usuário");
        user.setLastName("Default");
        user.setUsername(defaultUsername);
        user.setEmail("default1@hospital.com");
        user.setPassword(passwordEncoder.encode("admin123"));
        user.setRole("ADMIN");
        userRepository.save(user);
        System.out.println("Usuário default criado: default.user/admin123");
    }
}

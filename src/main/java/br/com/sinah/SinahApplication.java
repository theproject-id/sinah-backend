package br.com.sinah;

import br.com.sinah.user.model.UserModel;
import br.com.sinah.user.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

@SpringBootApplication
public class SinahApplication {

    public static void main(String[] args) {
        SpringApplication.run(SinahApplication.class, args);
    }

    @Bean
    CommandLineRunner createFirstUser(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        return args -> {
            String adminEmail = "admin@sinah.com.br";
            if (userRepository.findByEmail(adminEmail).isEmpty()) {
                String hashedPassword = encoder.encode("admin");
                UserModel admin = new UserModel();
                admin.setUuid(UUID.randomUUID());
                admin.setFirstName("SINAH");
                admin.setLastName("Admin");
                admin.setUsername("sinah");
                admin.setEmail("admin@sinah.com.br");
                admin.setPassword(hashedPassword);
                admin.setRole("ADMIN");
                userRepository.save(admin);
            }
        };
    }
}

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
            String adminEmail = "lucas@example.com";

            if (userRepository.findByEmail(adminEmail).isEmpty()) {
                String hashedPassword = encoder.encode("password"); // first-run password

                UserModel admin = new UserModel();
                admin.setUuid(UUID.randomUUID());
                admin.setFirstName("Admin");
                admin.setLastName("User");
                admin.setUsername("admin");
                admin.setEmail(adminEmail);
                admin.setPassword(hashedPassword);
                admin.setRole("ADMIN");

                userRepository.save(admin);

                System.out.println("First-run admin user created: " + adminEmail);
            } else {
                System.out.println("Admin user already exists.");
            }
        };
    }
}

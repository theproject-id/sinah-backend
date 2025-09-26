package br.com.sinah.user.service;

import br.com.sinah.user.model.UserModel;
import br.com.sinah.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel loadUserByUUID(UUID uuid) throws UsernameNotFoundException {
        return userRepository.findById(uuid).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
    }

    @Override
    public UserModel loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
    }
}

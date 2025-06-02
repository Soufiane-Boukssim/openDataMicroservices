package com.soufiane.authservice.configs;

import com.soufiane.authservice.entities.User;
import com.soufiane.authservice.enums.Role;
import com.soufiane.authservice.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner run(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        return args -> {
            if (userRepository.findFirstByEmail("soufianeboukssim@gmail.com")== null) {
                User admin = User.builder()
                        .firstName("Soufiane")
                        .lastName("Boukssim")
                        .email("soufianeboukssim@gmail.com")
                        .password(encoder.encode("admin123"))
                        .role(Role.ROLE_ADMIN)
                        .deleted(false)
                        .uuid(UUID.randomUUID())
                        .build();
                userRepository.save(admin);
            }
        };
    }
}
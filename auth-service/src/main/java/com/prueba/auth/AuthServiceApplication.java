package com.prueba.auth;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.prueba.auth.model.UserEntity;
import com.prueba.auth.repository.UserRepository;
@SpringBootApplication
public class AuthServiceApplication {
	
	@Bean("seedDefaultAdmin")
    public CommandLineRunner seedAdmin(UserRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (repo.findByUsername("admin").isEmpty()) {
                UserEntity admin = UserEntity.builder()
                    .username("admin")
                    .password(encoder.encode("adminpass"))
                    .roles(Set.of("ROLE_ADMIN"))
                    .build();
                repo.save(admin);
            }
        };
    }
	
    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }
}

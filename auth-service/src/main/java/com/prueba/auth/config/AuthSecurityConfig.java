package com.prueba.auth.config;


import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.prueba.auth.model.UserEntity;
import com.prueba.auth.repository.UserRepository;

@Configuration
public class AuthSecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf().disable()
      .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    .and()
      .authorizeHttpRequests(auth -> auth
        // 1) Login y JWKS públicos:
        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
        .requestMatchers(HttpMethod.GET, "/auth/.well-known/jwks.json").permitAll()
        // 2) Si en el futuro tienes un endpoint de refresco o registro:
        // .requestMatchers("/auth/refresh", "/auth/register").permitAll()

        // 3) Si quieres permitir el “ping”:
        .requestMatchers("/actuator/health", "/actuator/info").permitAll()

        // 4) Todo lo demás en AuthService requiere autenticación,
        //     pero no roles concretos:
        .anyRequest().authenticated()
      )
      .httpBasic().disable()
      .formLogin().disable();

    return http.build();
  }
  
  @Bean
  public CommandLineRunner seedAdmin(UserRepository userRepo,
                                     PasswordEncoder passwordEncoder) {
      return args -> {
          String adminUsername = "admin";
          if (userRepo.findByUsername(adminUsername).isEmpty()) {
              UserEntity admin = UserEntity.builder()
                  .username(adminUsername)
                  .password( passwordEncoder.encode("adminpass") )
                  .roles(Set.of("ROLE_ADMIN"))
                  .build();
              userRepo.save(admin);
              System.out.println("👷‍♂️ Admin creado con contraseña 'adminpass'");
          }
      };
  }
}
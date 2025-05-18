package com.prueba.practica.config;

import java.nio.charset.StandardCharsets;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;

import org.springframework.security.web.server.SecurityWebFilterChain;

import reactor.core.publisher.Mono;

@Configuration
public class GatewaySecurityConfig {

  @Value("${jwt.secret}")
  private String jwtSecret;

  // 1) Bean para decodificar el JWT con HS256
  @Bean
  public ReactiveJwtDecoder reactiveJwtDecoder() {
    byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
    SecretKeySpec key = new SecretKeySpec(keyBytes, "HmacSHA256");
    return NimbusReactiveJwtDecoder.withSecretKey(key).build();
  }

  // 2) Converter que extrae el claim "roles" como autoridades
  private Converter<Jwt, Mono<AbstractAuthenticationToken>> jwtAuthConverter() {
    JwtGrantedAuthoritiesConverter ga = new JwtGrantedAuthoritiesConverter();
    ga.setAuthorityPrefix("");          // ya vienen con "ROLE_" en el claim
    ga.setAuthoritiesClaimName("roles"); 

    JwtAuthenticationConverter authConverter = new JwtAuthenticationConverter();
    authConverter.setJwtGrantedAuthoritiesConverter(ga);

    return new ReactiveJwtAuthenticationConverterAdapter(authConverter);
  }

  // 3) La regla de seguridad
  @Bean
  public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
    http
      .csrf().disable()
      .authorizeExchange(ex -> ex
        // login y rutas públicas
        .pathMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
        .pathMatchers(HttpMethod.GET,  "/api/**").permitAll()

        // sólo ADMIN para modificaciones
        .pathMatchers(HttpMethod.POST,   "/api/**").hasRole("ADMIN")
        .pathMatchers(HttpMethod.PUT,    "/api/**").hasRole("ADMIN")
        .pathMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")

        .anyExchange().authenticated()
      )
      .oauth2ResourceServer(oauth -> oauth
        .jwt(jwt -> jwt
          .jwtAuthenticationConverter(jwtAuthConverter())
        )
      );
    return http.build();
  }
}

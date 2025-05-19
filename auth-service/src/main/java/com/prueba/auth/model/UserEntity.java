package com.prueba.auth.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.*;

@Entity
@Table(name = "usuarios")  // en minúsculas y snake_case
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuarioid")         // coincidiendo con el DDL
    private Long usuarioID;

    @Column(name = "nombreusuario",      // snake_case
            unique = true,
            nullable = false)
    private String username;

    @Column(name = "contrasena",          // snake_case
            nullable = false)
    private String password;

    @Column(name = "fechacreacion",      // snake_case
            nullable = false,
            updatable = false)
    @Builder.Default
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
      name = "usuarioroles",              // tabla en minúsculas
      joinColumns = @JoinColumn(name = "usuarioid")
    )
    @Column(name = "rol")                  // columna en minúsculas
    private Set<String> roles;
}

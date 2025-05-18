package com.prueba.auth.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.*;

@Entity
@Table(name = "Usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioID;

    @Column(name = "NombreUsuario", unique = true, nullable = false)
    private String username;

    @Column(name = "Contrasena", nullable = false)
    private String password;

    @Column(name = "FechaCreacion", nullable = false, updatable = false)
    @Builder.Default
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
      name = "UsuarioRoles",
      joinColumns = @JoinColumn(name = "UsuarioID")
    )
    @Column(name = "Rol")
    private Set<String> roles;
}
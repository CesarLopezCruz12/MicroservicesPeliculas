package com.prueba.character.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "personajes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CharacterModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personajeid")     // columna correcta
    private Integer personajeID;

    @Column(name = "nombre")           // si usas snake_case en BD
    private String nombre;

    @Column(name = "imagen", columnDefinition = "TEXT")
    private String imagen;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @CreationTimestamp
    @Column(name = "fechacreacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;
}

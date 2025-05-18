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
@Table(name = "Personajes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CharacterModel {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PersonajeID")
    private Integer personajeID;

    @Column(name = "Nombre", nullable = false)
    private String nombre;
    
    @Column(name = "Imagen", columnDefinition = "TEXT")
    private String imagen;
    
    @Lob
    @Column(name = "Descripcion")
    private String descripcion;

    @CreationTimestamp
    @Column(name = "FechaCreacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;
}

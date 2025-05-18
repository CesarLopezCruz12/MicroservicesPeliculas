package com.prueba.movie.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "Multimedia")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MultimediaID")
    private Integer multimediaID;

    @Column(name = "Titulo", nullable = false)
    private String titulo;

    
    @Column(name = "TipoMultimediaID", nullable = false)
    private Integer tipoMultimediaID;

    @Column(name = "Imagen", columnDefinition = "TEXT")
    private String imagen;

    @Lob
    @Column(name = "Descripcion")
    private String descripcion;

    @CreationTimestamp
    @Column(name = "FechaCreacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;
}

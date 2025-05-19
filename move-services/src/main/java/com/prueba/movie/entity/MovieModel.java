package com.prueba.movie.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "multimedia")  // en minúsculas y snake_case
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "multimediaid")
    private Integer multimediaID;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "tipomultimediaid", nullable = false)  // ← aquí cambias
    private Integer tipoMultimediaID;

    @Column(name = "imagen", columnDefinition = "TEXT")
    private String imagen;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @CreationTimestamp
    @Column(name = "fechacreacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;
}

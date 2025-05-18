package com.prueba.casting.entity;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Reparto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CastingModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RepartoID")
    private Integer repartoID;
	

    @Column(name = "MultimediaID", nullable = false)
    private Integer multimediaID;
    
    @Column(name = "PersonajeID", nullable = false)
    private Integer  personajeID;
    
    @Column(name = "Rol", nullable = true)
    private String rol;
    
    @Lob
    @Column(name = "Descripcion")
    private String descripcion;
}

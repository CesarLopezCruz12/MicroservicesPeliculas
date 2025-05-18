package com.prueba.bbf.dto;

import java.time.LocalDateTime;

public record CharacterDTO (
		Integer personajeID,
		String nombre,
		String imagen,
		String descripcion,
		LocalDateTime fechaCreacion
	) {}

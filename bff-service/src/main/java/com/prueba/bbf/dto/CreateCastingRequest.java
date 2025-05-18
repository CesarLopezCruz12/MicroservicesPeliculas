package com.prueba.bbf.dto;

public record CreateCastingRequest(
		Integer multimediaID,
		Integer personajeID,
		String rol,
		String descripcion
) {}
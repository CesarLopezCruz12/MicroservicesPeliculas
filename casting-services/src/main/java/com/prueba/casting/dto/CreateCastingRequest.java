package com.prueba.casting.dto;

public record CreateCastingRequest(
		Integer multimediaID,
		Integer personajeID,
		String rol,
		String descripcion
) {}
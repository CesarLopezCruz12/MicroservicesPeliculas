package com.prueba.bbf.dto;

import java.util.List;

public record CreateCharacterCastingRequest(
		String nombre,
		String imagen,
		String descripcion,
		List<CreateCastingRequest> casting
) {}

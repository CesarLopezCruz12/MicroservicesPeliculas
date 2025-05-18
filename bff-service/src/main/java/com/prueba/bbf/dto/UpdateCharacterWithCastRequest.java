package com.prueba.bbf.dto;

import java.util.List;
import jakarta.validation.constraints.NotNull;

public record UpdateCharacterWithCastRequest(
	    Integer personajeID,
	    String nombre,
	    String imagen,
	    String descripcion,
	    List<CreateCastingRequest> casting
	) {}
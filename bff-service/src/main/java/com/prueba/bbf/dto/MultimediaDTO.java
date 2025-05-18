package com.prueba.bbf.dto;

import java.time.LocalDateTime;

public record MultimediaDTO(
	    Integer multimediaID,
	    Integer tipoMultimediaID,
	    String titulo,
	    String imagen,
	    String descripcion,
	    LocalDateTime fechaCreacion
	) {}

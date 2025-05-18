package com.prueba.bbf.dto;

import java.time.LocalDateTime;

public record MovieDTO(
	    Integer multimediaID,
	    Integer tipoMultimediaID,
	    String titulo,
	    String imagen,
	    String descripcion,
	    LocalDateTime fechaCreacion
	) {}

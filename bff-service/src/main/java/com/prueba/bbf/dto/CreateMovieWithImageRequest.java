package com.prueba.bbf.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateMovieWithImageRequest(
	    @NotNull Integer tipoMultimediaID,
	    @NotBlank String titulo,
	    @NotBlank String descripcion
	) {}
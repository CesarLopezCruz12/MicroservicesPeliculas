package com.prueba.bbf.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record UpdateMovieWithImageRequest(
	    @NotNull Integer multimediaID,        // el ID de la película
	    @NotNull Integer tipoMultimediaID,
	    @NotBlank String titulo,
	    String descripcion
	) {}

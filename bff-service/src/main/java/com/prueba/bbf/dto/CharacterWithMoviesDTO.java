package com.prueba.bbf.dto;

import java.util.List;

public record CharacterWithMoviesDTO(
		  CharacterDTO personaje,
		  List<CastingDTO> reparto,
		  List<MultimediaDTO> peliculas
		) {}
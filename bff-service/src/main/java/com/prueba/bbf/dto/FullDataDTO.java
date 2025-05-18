package com.prueba.bbf.dto;

import java.util.List;

public record FullDataDTO(
		  List<MultimediaDTO> peliculas,
		  List<CharacterDTO> personajes,
		  List<CastingDTO> reparto
		) {}
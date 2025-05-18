package com.prueba.bbf.dto;

import java.util.List;

public record MovieWithCastDTO(
		  MultimediaDTO pelicula,
		  List<CastingDTO> reparto,
		  List<CharacterDTO> personajes
		) {}
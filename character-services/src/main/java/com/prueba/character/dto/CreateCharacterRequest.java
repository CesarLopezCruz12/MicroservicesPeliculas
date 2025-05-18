package com.prueba.character.dto;

public record CreateCharacterRequest (
		String nombre,
		String imagen,
		String descripcion
) {}

package com.prueba.bbf.dto;

public record CreateCharacterRequest (
		String nombre,
		String imagen,
		String descripcion
) {}
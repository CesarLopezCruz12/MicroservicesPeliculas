package com.prueba.auth.dto;


public record LoginRequest(
		String username, 
		String password
) {}

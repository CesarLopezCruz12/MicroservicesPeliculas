package com.prueba.character;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CharacterServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CharacterServicesApplication.class, args);
	}

}

package com.prueba.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MoveServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoveServicesApplication.class, args);
	}

}

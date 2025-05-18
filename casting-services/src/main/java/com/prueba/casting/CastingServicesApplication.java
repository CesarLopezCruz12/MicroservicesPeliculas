package com.prueba.casting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CastingServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CastingServicesApplication.class, args);
	}

}

package com.prueba.bbf.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.prueba.bbf.dto.CharacterDTO;
import com.prueba.bbf.dto.CreateCharacterRequest;


@FeignClient(name="personaje-service", url="${services.personaje.url}")
public interface CharacterClient {
	
	@PostMapping("/personajes")
    CharacterDTO create(@RequestBody CreateCharacterRequest req);
	
	@PutMapping("/personajes/{id}")
    CharacterDTO update(
        @PathVariable("id") Integer id,
        @RequestBody CreateCharacterRequest req
    );
	
	@GetMapping("/personajes/{id}")
    CharacterDTO get(@PathVariable("id") Integer id);
	
	@GetMapping("/personajes")
    List<CharacterDTO> findAll();
	
	
}
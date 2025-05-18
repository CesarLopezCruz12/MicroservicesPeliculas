package com.prueba.character.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.character.dto.CharacterDTO;
import com.prueba.character.dto.CreateCharacterRequest;
import com.prueba.character.service.CharacterService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/personajes")
public class CharacterController {
	
	private final CharacterService srv;

	public CharacterController(CharacterService srv) {
		this.srv = srv;
	}
	
	@GetMapping
    public List<CharacterDTO> list(@RequestParam(defaultValue = "fecha") String orden) {
        return srv.listAll(orden);
    }

    @GetMapping("/{id}")
    public CharacterDTO get(@PathVariable Integer id) {
        return srv.findById(id);
    }

    @PostMapping
    public ResponseEntity<CharacterDTO> create(
            @RequestBody @Valid CreateCharacterRequest req) {
    	CharacterDTO dto = srv.create(req);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public CharacterDTO update(
            @PathVariable Integer id,
            @RequestBody @Valid CreateCharacterRequest req) {
        return srv.update(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> delete(@PathVariable Integer id) {
        srv.delete(id);
        Map<String,String> resp = Map.of(
            "message", 
            "Character with ID: " + id + " was eliminated"
        );
        return ResponseEntity.ok(resp);
    } 
}

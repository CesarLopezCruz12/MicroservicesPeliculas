package com.prueba.casting.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.prueba.casting.dto.CastingDTO;
import com.prueba.casting.dto.CreateCastingRequest;
import com.prueba.casting.service.CastingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reparto")
public class CastingController {

	private final CastingService srv;
	
	public CastingController(CastingService srv) {
		this.srv = srv;
	}
	
	@GetMapping
    public List<CastingDTO> findAll() {
        return srv.findAll();
    }

	
	@GetMapping("/multimedia/{multimediaID}")
    public List<CastingDTO> getByMultimediaID(@PathVariable Integer multimediaID) {
        return srv.findByMultimediaID(multimediaID);
    }
	
	@GetMapping("/personaje/{personajeID}")
    public List<CastingDTO> getByPersonajeID(@PathVariable Integer personajeID) {
        return srv.findByPersonajeID(personajeID);
    }
	
	@PostMapping
    public ResponseEntity<CastingDTO> create(
            @RequestBody @Valid CreateCastingRequest req) {
		CastingDTO dto = srv.create(req);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
	
	@PutMapping("/{id}")
    public CastingDTO update(
            @PathVariable Integer id,
            @RequestBody @Valid CreateCastingRequest req) {
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
    
    @DeleteMapping("/personaje/{personajeID}")
    public ResponseEntity<Void> deleteByPersonaje(
        @PathVariable Integer personajeID
    ) {
      srv.deleteByPersonajeID(personajeID);
      return ResponseEntity.noContent().build();
    }
	
}

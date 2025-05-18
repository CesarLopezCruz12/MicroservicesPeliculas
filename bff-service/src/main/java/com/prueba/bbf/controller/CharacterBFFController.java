package com.prueba.bbf.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.bbf.dto.CharacterDTO;
import com.prueba.bbf.dto.CreateCharacterCastingRequest;
import com.prueba.bbf.dto.CreateCharacterRequest;
import com.prueba.bbf.dto.CreateCharacterWithCastAndImageRequest;
import com.prueba.bbf.dto.UpdateCharacterWithCastAndImageRequest;
import com.prueba.bbf.dto.UpdateCharacterWithCastRequest;
import com.prueba.bbf.service.CharacterBFFService;

@RestController
@RequestMapping("/bff/personajes")
public class CharacterBFFController {
	
	private final CharacterBFFService service;
	private final ObjectMapper objectMapper;

	  public CharacterBFFController(CharacterBFFService service,
			  						ObjectMapper objectMapper) {
	    this.service = service;
	    this.objectMapper = objectMapper;
	  }

	  @PostMapping
	  @ResponseStatus(HttpStatus.CREATED)
	  public CharacterDTO create(@RequestBody @Valid CreateCharacterRequest req) {
	    return service.createPersonaje(req);
	  }
	  
	  
	  @PostMapping("/conreparto")
	    @ResponseStatus(HttpStatus.CREATED)
	    public CharacterDTO createWithCast(@RequestBody @Valid CreateCharacterCastingRequest req) {
	        return service.createPersonajeWithCast(req);
	    }
	  
	  @PostMapping(
		      path = "/conreparto-with-image",
		      consumes = "multipart/form-data"
		    )
		    @ResponseStatus(HttpStatus.CREATED)
		    public CharacterDTO createWithCastAndImage(
		        @RequestParam("data") @Valid String jsonData,
		        @RequestParam("file") MultipartFile file
		    ) throws Exception {
		        // 1) parseamos el JSON a nuestro DTO
		        CreateCharacterWithCastAndImageRequest dto =
		            objectMapper.readValue(jsonData, CreateCharacterWithCastAndImageRequest.class);

		        // 2) delegamos al service
		        return service.createPersonajeWithCastAndImage(dto, file);
		    }

	  
	  @PutMapping("/conreparto")
	    @ResponseStatus(HttpStatus.OK)
	    public CharacterDTO updateWithCast(@RequestBody @Valid UpdateCharacterWithCastRequest req) {
	        return service.updateCharacterWithCast(req);
	    }
	  
	  @PutMapping(path = "/conreparto-with-image",
              consumes = "multipart/form-data")
	  @ResponseStatus(HttpStatus.OK)
	  public CharacterDTO updateWithCastAndImage(
	      @RequestParam("data") @Valid String jsonData,
	      @RequestParam("file") MultipartFile file
	  ) throws Exception {
	      // 1) Deserializar JSON a DTO de update
	      UpdateCharacterWithCastAndImageRequest dto =
	          objectMapper.readValue(jsonData, UpdateCharacterWithCastAndImageRequest.class);
	
	      // 2) Delegar al service
	      return service.updatePersonajeWithCastAndImage(dto, file);
	  }
}

package com.prueba.bbf.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.bbf.dto.CharacterWithMoviesDTO;
import com.prueba.bbf.dto.FullDataDTO;
import com.prueba.bbf.dto.MovieWithCastDTO;
import com.prueba.bbf.service.InfoBFFService;

@RestController
@RequestMapping("/bff/info")
public class InfoBFFController {
	
	
	private final InfoBFFService svc;

    public InfoBFFController(InfoBFFService svc) {
        this.svc = svc;
    }
    
	@GetMapping("/full")
    public FullDataDTO full() {
        return svc.findAll();
    }

	
	@GetMapping("/peliculas/reparto")
	public List<MovieWithCastDTO> moviesWithCast() {
	  return svc.findAllMoviesWithCast();
	}

	@GetMapping("/personajes/peliculas")
	public List<CharacterWithMoviesDTO> charactersWithMovies() {
	  return svc.findAllCharactersWithMovies();
	}
	
	
	@GetMapping("/peliculas/reparto/{id}")
	public MovieWithCastDTO moviesWithCastByID(@PathVariable("id") Integer multimediaID) {
	  return svc.findByIdMoviesWithCast(multimediaID);
	}

	@GetMapping("/personajes/peliculas/{id}")
	public CharacterWithMoviesDTO charactersWithMoviesByID(@PathVariable("id") Integer characterID) {
	  return svc.findByIdCharactersWithMovies(characterID);
	}
}

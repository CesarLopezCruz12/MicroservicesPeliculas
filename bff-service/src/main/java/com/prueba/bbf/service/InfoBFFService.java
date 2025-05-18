package com.prueba.bbf.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.prueba.bbf.client.CastingClient;
import java.util.stream.Collectors;
import com.prueba.bbf.client.CharacterClient;
import com.prueba.bbf.client.MultimediaClient;
import com.prueba.bbf.dto.CharacterDTO;
import com.prueba.bbf.dto.CharacterWithMoviesDTO;
import com.prueba.bbf.dto.CastingDTO;
import com.prueba.bbf.dto.FullDataDTO;
import com.prueba.bbf.dto.MovieWithCastDTO;
import com.prueba.bbf.dto.MultimediaDTO;

import feign.FeignException;

@Service
public class InfoBFFService {
	
	private final CharacterClient characterClient;
	private final CastingClient castingClient;
	private final MultimediaClient multimediaClient;  
	
	public InfoBFFService(MultimediaClient multimediaClient,
				            CharacterClient characterClient,
				            CastingClient castingClient) {
				this.multimediaClient = multimediaClient;
				this.characterClient  = characterClient;
				this.castingClient    = castingClient;
				}
	
	 public FullDataDTO findAll() {
	        List<MultimediaDTO> peliculas  = multimediaClient.findAll();
	        List<CharacterDTO>  personajes = characterClient.findAll();
	        List<CastingDTO>     repartoAll = castingClient.findAll();
	        return new FullDataDTO(peliculas, personajes, repartoAll);
	    }

	 public List<MovieWithCastDTO> findAllMoviesWithCast() {
	        return multimediaClient.findAll().stream()
	          .map(m -> {
	              List<CastingDTO> cast = castingClient.byMovie(m.multimediaID());
	              List<CharacterDTO> chars = cast.stream()
	                  .map(c -> characterClient.get(c.personajeID()))
	                  .collect(Collectors.toList());
	              return new MovieWithCastDTO(m, cast, chars);
	          })
	          .collect(Collectors.toList());
	    }

	    
    public List<CharacterWithMoviesDTO> findAllCharactersWithMovies() {
        return characterClient.findAll().stream()
          .map(p -> {
              List<CastingDTO> cast = castingClient.byChar(p.personajeID());
              List<MultimediaDTO> movies = cast.stream()
                  .map(c -> multimediaClient.get(c.multimediaID()))
                  .collect(Collectors.toList());
              return new CharacterWithMoviesDTO(p, cast, movies);
          })
          .collect(Collectors.toList());
    }
    
    public MovieWithCastDTO findByIdMoviesWithCast(Integer multimediaID) {
        MultimediaDTO peli = multimediaClient.get(multimediaID);
        List<CastingDTO> cast;
        try {
            cast = castingClient.byMovie(multimediaID);
        } catch (FeignException.NotFound e) {
            cast = Collections.emptyList();
        }
        List<CharacterDTO> personajes = cast.stream()
            .map(c -> characterClient.get(c.personajeID()))
            .collect(Collectors.toList());

        return new MovieWithCastDTO(peli, cast, personajes);
    }
    public CharacterWithMoviesDTO findByIdCharactersWithMovies(Integer characterID) {
        CharacterDTO p = characterClient.get(characterID);
        List<CastingDTO> cast = castingClient.byChar(characterID);
        List<MultimediaDTO> movies = cast.stream()
            .map(c -> multimediaClient.get(c.multimediaID()))
            .collect(Collectors.toList());
        return new CharacterWithMoviesDTO(p, cast, movies);
    }
	
}

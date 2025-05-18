package com.prueba.bbf.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.prueba.bbf.dto.CreateMovieRequest;
import com.prueba.bbf.dto.MovieDTO;
import com.prueba.bbf.dto.MultimediaDTO;

@FeignClient(name="multimedia-service", url="${services.multimedia.url}")
public interface MultimediaClient {
	
  @GetMapping("/peliculas")
  List<MultimediaDTO> findAll();
 
  @GetMapping("/peliculas/{id}")
  MultimediaDTO get(@PathVariable("id") Integer id);
  
  @PostMapping("/peliculas")
  MovieDTO create(@RequestBody CreateMovieRequest req);

  @PutMapping("/peliculas/{id}")
  MovieDTO update(@PathVariable("id") Integer id, @RequestBody CreateMovieRequest req);

  
}
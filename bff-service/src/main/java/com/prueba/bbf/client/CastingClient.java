package com.prueba.bbf.client;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.prueba.bbf.dto.CastingDTO;
import com.prueba.bbf.dto.CreateCastingRequest;


@FeignClient(name="casting-service", url="${services.casting.url}")
public interface CastingClient {
	
  @PostMapping("/reparto")
  CastingDTO create(@RequestBody CreateCastingRequest req);

  @GetMapping("/reparto/multimedia/{multimediaID}")
  List<CastingDTO> byMovie(@PathVariable Integer multimediaID);

  @GetMapping("/reparto/personaje/{personajeID}")
  List<CastingDTO> byChar(@PathVariable Integer personajeID);
  
  @DeleteMapping("/reparto/personaje/{personajeID}")
  void deleteByPersonaje(@PathVariable Integer personajeID);
  
  @GetMapping("/reparto")
  List<CastingDTO> findAll();
}

package com.prueba.casting.service;

import java.util.List;

import com.prueba.casting.dto.CastingDTO;
import com.prueba.casting.dto.CreateCastingRequest;


public interface CastingService {
	
	List<CastingDTO> findAll();
	List<CastingDTO> findByMultimediaID(Integer multimediaID);
	List<CastingDTO> findByPersonajeID(Integer personajeID);
	CastingDTO create(CreateCastingRequest req);
	CastingDTO update(Integer id, CreateCastingRequest req);
    void delete(Integer id);
    void deleteByPersonajeID(Integer personajeID);
}

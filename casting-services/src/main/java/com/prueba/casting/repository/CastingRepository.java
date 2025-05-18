package com.prueba.casting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.casting.dto.CastingDTO;
import com.prueba.casting.entity.CastingModel;


@Repository
public interface CastingRepository extends JpaRepository<CastingModel,Integer>{
	
	List<CastingModel> findAll();
	List<CastingModel> findByMultimediaID(Integer multimediaID);
	List<CastingModel> findByPersonajeID(Integer personajeID);
	void deleteAllByPersonajeID(Integer personajeID);
}

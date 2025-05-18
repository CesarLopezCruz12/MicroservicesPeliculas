package com.prueba.casting.mapper;

import org.springframework.stereotype.Component;

import com.prueba.casting.dto.CastingDTO;
import com.prueba.casting.dto.CreateCastingRequest;
import com.prueba.casting.entity.CastingModel;

@Component
public class CastingMapper {
	public CastingDTO toDTO(CastingModel c) {
		return new CastingDTO(
				c.getRepartoID(),
				c.getMultimediaID(),
				c.getPersonajeID(),
				c.getRol(),
				c.getDescripcion()
				);
	}
	
	public CastingModel toEntity(CreateCastingRequest req) {
		CastingModel cm = new CastingModel();
		cm.setMultimediaID(req.multimediaID());
		cm.setPersonajeID(req.personajeID());
		cm.setRol(req.rol());
		cm.setDescripcion(req.descripcion());
		return cm;
	}
}

package com.prueba.character.mapper;

import org.springframework.stereotype.Component;

import com.prueba.character.dto.CharacterDTO;
import com.prueba.character.dto.CreateCharacterRequest;
import com.prueba.character.entity.CharacterModel;

@Component
public class CharacterMapper {
	
	public CharacterDTO toDTO(CharacterModel c) {
		return new CharacterDTO(
					c.getPersonajeID(),
					c.getNombre(),
					c.getImagen(),
					c.getDescripcion(),
					c.getFechaCreacion()
				);
	}
	
	public CharacterModel toEntity(CreateCharacterRequest req) {
		CharacterModel cm = new CharacterModel();
		cm.setNombre(req.nombre());
		cm.setImagen(req.imagen());
		cm.setDescripcion(req.descripcion());
		return cm;
	}
}

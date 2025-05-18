package com.prueba.character.service;

import java.util.List;

import com.prueba.character.dto.CharacterDTO;
import com.prueba.character.dto.CreateCharacterRequest;

public interface CharacterService {
	List<CharacterDTO> listAll(String orden);  
	CharacterDTO findById(Integer id);
	CharacterDTO create(CreateCharacterRequest req);
	CharacterDTO update(Integer id, CreateCharacterRequest req);
    void delete(Integer id);
}

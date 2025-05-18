package com.prueba.character.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.prueba.character.dto.CharacterDTO;
import com.prueba.character.dto.CreateCharacterRequest;
import com.prueba.character.entity.CharacterModel;
import com.prueba.character.mapper.CharacterMapper;
import com.prueba.character.repository.CharacterRepository;


@Service
public class CharacterServiceImpl  implements CharacterService{
	private final CharacterRepository repo;
    private final CharacterMapper mapper;

    public CharacterServiceImpl(CharacterRepository repo, CharacterMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    @CacheEvict(value = "character", allEntries = true)
    public List<CharacterDTO> listAll(String orden) {
        Sort sort = "nombre".equalsIgnoreCase(orden)
            ? Sort.by("nombre")
            : Sort.by("fechaCreacion").descending();
        return repo.findAll(sort)
                   .stream()
                   .map(mapper::toDTO)
                   .toList();
    }

    @Override
    @CacheEvict(value = "character", allEntries = true)
    public CharacterDTO findById(Integer id) {
    	CharacterModel m = repo.findById(id)
            .orElseThrow(() -> 
                new ResponseStatusException(
                    HttpStatus.NOT_FOUND, 
                    "Character not found: " + id
                )
            );
        return mapper.toDTO(m);
    }

    @Override
    @CacheEvict(value = "character", allEntries = true)
    public CharacterDTO create(CreateCharacterRequest req) {
    	CharacterModel saved = repo.save(mapper.toEntity(req));
        return mapper.toDTO(saved);
    }

    @Override
    @CacheEvict(value = "character", allEntries = true)
    public CharacterDTO update(Integer id, CreateCharacterRequest req) {
    	CharacterModel existing = repo.findById(id)
        .orElseThrow(() ->
          new ResponseStatusException(HttpStatus.NOT_FOUND,
                                     "Character not found: " + id));
      existing.setNombre(req.nombre());
      existing.setImagen(req.imagen());
      existing.setDescripcion(req.descripcion());
      CharacterModel updated = repo.save(existing);
      return mapper.toDTO(updated);
    }

    @Override
    @CacheEvict(value = "character", allEntries = true)
    public void delete(Integer id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Character not found: " + id
            );
        }
        repo.deleteById(id);
    }
}


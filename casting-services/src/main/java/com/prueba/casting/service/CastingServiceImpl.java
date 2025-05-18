package com.prueba.casting.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.prueba.casting.dto.CastingDTO;
import com.prueba.casting.dto.CreateCastingRequest;
import com.prueba.casting.entity.CastingModel;
import com.prueba.casting.mapper.CastingMapper;
import com.prueba.casting.repository.CastingRepository;

import jakarta.transaction.Transactional;

import org.springframework.cache.annotation.Cacheable;

@Service
public class CastingServiceImpl implements CastingService{
	private final CastingRepository repo;
	private final CastingMapper mapper;
	
	public CastingServiceImpl(CastingRepository repo, CastingMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}
	
	@Override
    @Cacheable(value = "casting", key = "'all'")
    public List<CastingDTO> findAll() {
        return repo.findAll().stream()
                   .map(mapper::toDTO)
                   .collect(Collectors.toList());
    }

	@Override
    @Cacheable(value = "casting", key = "#multimediaID")
    public List<CastingDTO> findByMultimediaID(Integer multimediaID) {
        List<CastingModel> reparto = repo.findByMultimediaID(multimediaID);
        if (reparto.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "No Data to Movies with Character: " + multimediaID
            );
        }
        return reparto.stream()
                      .map(mapper::toDTO)
                      .collect(Collectors.toList());
    }
	
	@Override
	@Cacheable(value = "casting", key = "#personajeID")
	public List<CastingDTO> findByPersonajeID(Integer personajeID) {
	    return repo.findByPersonajeID(personajeID).stream()
	               .map(mapper::toDTO)
	               .collect(Collectors.toList());
	}

	
	
    @Override
    @CacheEvict(value = "casting", allEntries = true)
    public CastingDTO create(CreateCastingRequest req) {
    	CastingModel saved = repo.save(mapper.toEntity(req));
        return mapper.toDTO(saved);
    }

    @Override
    @CacheEvict(value = "casting", allEntries = true)
    public CastingDTO update(Integer id, CreateCastingRequest req) {
    	CastingModel existing = repo.findById(id)
        .orElseThrow(() ->
          new ResponseStatusException(HttpStatus.NOT_FOUND,
                                     "Casting not found: " + id));
      existing.setMultimediaID(req.multimediaID());
      existing.setPersonajeID(req.personajeID());
      existing.setRol(req.rol());
      existing.setDescripcion(req.descripcion());
      CastingModel updated = repo.save(existing);
      return mapper.toDTO(updated);
    }

    @Override
    @CacheEvict(value = "casting", allEntries = true)
    public void delete(Integer id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Casting not found: " + id
            );
        }
        repo.deleteById(id);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = "casting", allEntries = true)
    public void deleteByPersonajeID(Integer personajeID) {
        repo.deleteAllByPersonajeID(personajeID);
    }
    
    
}

package com.prueba.movie.service;

import com.prueba.movie.repository.MovieRepository;
import com.prueba.movie.mapper.MovieMapper;
import com.prueba.movie.entity.MovieModel;
import com.prueba.movie.dto.MovieDTO;
import com.prueba.movie.dto.CreateMovieRequest;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository repo;
    private final MovieMapper mapper;

    public MovieServiceImpl(MovieRepository repo, MovieMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    @CacheEvict(value = "movies", allEntries = true)
    public List<MovieDTO> listAll(String orden) {
        Sort sort = "titulo".equalsIgnoreCase(orden)
            ? Sort.by("titulo")
            : Sort.by("fechaCreacion").descending();
        return repo.findAll(sort)
                   .stream()
                   .map(mapper::toDTO)
                   .toList();
    }

    @Override
    @CacheEvict(value = "movies", allEntries = true)
    public MovieDTO findById(Integer id) {
        MovieModel m = repo.findById(id)
            .orElseThrow(() -> 
                new ResponseStatusException(
                    HttpStatus.NOT_FOUND, 
                    "Movie not found: " + id
                )
            );
        return mapper.toDTO(m);
    }

    @Override
    @CacheEvict(value = "movies", allEntries = true)
    public MovieDTO create(CreateMovieRequest req) {
        MovieModel saved = repo.save(mapper.toEntity(req));
        return mapper.toDTO(saved);
    }

    @Override
    @CacheEvict(value = "movies", allEntries = true)
    public MovieDTO update(Integer id, CreateMovieRequest req) {
      MovieModel existing = repo.findById(id)
        .orElseThrow(() ->
          new ResponseStatusException(HttpStatus.NOT_FOUND,
                                     "Movie not found: " + id));
      existing.setTipoMultimediaID(req.tipoMultimediaID());
      existing.setTitulo(req.titulo());
      existing.setImagen(req.imagen());
      existing.setDescripcion(req.descripcion());
      MovieModel updated = repo.save(existing);
      return mapper.toDTO(updated);
    }

    @Override
    @CacheEvict(value = "movies", allEntries = true)
    public void delete(Integer id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Movie not found: " + id
            );
        }
        repo.deleteById(id);
    }
}

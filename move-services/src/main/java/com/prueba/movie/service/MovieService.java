package com.prueba.movie.service;

import com.prueba.movie.dto.MovieDTO;
import com.prueba.movie.dto.CreateMovieRequest;

import java.util.List;

public interface MovieService {
    List<MovieDTO> listAll(String orden);  
    MovieDTO findById(Integer id);
    MovieDTO create(CreateMovieRequest req);
    MovieDTO update(Integer id, CreateMovieRequest req);
    void delete(Integer id);
}

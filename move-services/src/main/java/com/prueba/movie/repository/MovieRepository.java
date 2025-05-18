package com.prueba.movie.repository;


import com.prueba.movie.entity.MovieModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieModel,Integer> {
    
}
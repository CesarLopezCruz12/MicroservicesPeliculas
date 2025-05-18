package com.prueba.movie.controller;

import com.prueba.movie.dto.MovieDTO;
import com.prueba.movie.dto.CreateMovieRequest;
import com.prueba.movie.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/peliculas")
public class MovieController {

    private final MovieService srv;

    public MovieController(MovieService srv) {
        this.srv = srv;
    }

    @GetMapping
    public List<MovieDTO> list(@RequestParam(defaultValue = "fecha") String orden) {
        return srv.listAll(orden);
    }

    @GetMapping("/{id}")
    public MovieDTO get(@PathVariable Integer id) {
        return srv.findById(id);
    }

    @PostMapping
    public ResponseEntity<MovieDTO> create(
            @RequestBody @Valid CreateMovieRequest req) {
        MovieDTO dto = srv.create(req);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public MovieDTO update(
            @PathVariable Integer id,
            @RequestBody @Valid CreateMovieRequest req) {
        return srv.update(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> delete(@PathVariable Integer id) {
        srv.delete(id);
        Map<String,String> resp = Map.of(
            "message", 
            "Movie with ID: " + id + " was eliminated"
        );
        return ResponseEntity.ok(resp);
    }

}

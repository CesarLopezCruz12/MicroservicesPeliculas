package com.prueba.bbf.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.bbf.dto.MovieDTO;
import com.prueba.bbf.dto.MultimediaDTO;
import com.prueba.bbf.dto.UpdateMovieWithImageRequest;
import com.prueba.bbf.service.MultimediaBFFService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/bff/peliculas")
public class MultimediaBFFController {
	
	private final MultimediaBFFService service;
	private final ObjectMapper objectMapper;

    public MultimediaBFFController(MultimediaBFFService service,
    								ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    // Create con imagen
    @PostMapping(path = "/with-image", consumes = "multipart/form-data")
    @ResponseStatus(HttpStatus.CREATED)
    public MovieDTO createWithImage(
        @RequestParam("data") @Valid String jsonData,
        @RequestParam("file") MultipartFile file
    ) throws Exception {
        return service.createWithImage(jsonData, file);
    }

    @PutMapping(path = "/with-image", consumes = "multipart/form-data")
    @ResponseStatus(HttpStatus.OK)
    public MovieDTO updateWithImage( @RequestParam("data") @Valid String jsonData, @RequestParam("file") MultipartFile file ) throws Exception {
        UpdateMovieWithImageRequest req = objectMapper.readValue(jsonData, UpdateMovieWithImageRequest.class);
        System.out.print(file);
        return service.updateWithImage(req, file);
    }

    // Passthrough a list y get
    @GetMapping
    public List<MultimediaDTO> list(@RequestParam(defaultValue="fecha") String orden) {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public MultimediaDTO get(@PathVariable Integer id) {
        return service.findById(id);
    }
}

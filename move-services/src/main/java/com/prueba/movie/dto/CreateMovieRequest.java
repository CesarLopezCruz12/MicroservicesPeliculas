package com.prueba.movie.dto;

public record CreateMovieRequest(
    Integer tipoMultimediaID,
    String titulo,
    String imagen,
    String descripcion
) {}

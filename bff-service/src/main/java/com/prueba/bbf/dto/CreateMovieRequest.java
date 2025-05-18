package com.prueba.bbf.dto;


public record CreateMovieRequest(
    Integer tipoMultimediaID,
    String titulo,
    String imagen,
    String descripcion
) {}

package com.prueba.movie.mapper;

import com.prueba.movie.entity.MovieModel;
import com.prueba.movie.dto.MovieDTO;
import com.prueba.movie.dto.CreateMovieRequest;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public MovieDTO toDTO(MovieModel m) {
        return new MovieDTO(
            m.getMultimediaID(),
            m.getTipoMultimediaID(),
            m.getTitulo(),
            m.getImagen(),
            m.getDescripcion(),
            m.getFechaCreacion()
        );
    }

    public MovieModel toEntity(CreateMovieRequest req) {
        MovieModel m = new MovieModel();
        m.setTipoMultimediaID(req.tipoMultimediaID());
        m.setTitulo(req.titulo());
        m.setImagen(req.imagen());
        m.setDescripcion(req.descripcion());
        // fechaCreacion se inicializa por defecto en la entidad
        return m;
    }
}

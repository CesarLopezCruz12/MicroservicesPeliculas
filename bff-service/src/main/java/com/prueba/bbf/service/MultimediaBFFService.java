package com.prueba.bbf.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.bbf.client.ImageClient;
import com.prueba.bbf.client.MultimediaClient;
import com.prueba.bbf.dto.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MultimediaBFFService {

	private final MultimediaClient multimediaClient;
    private final ImageClient imageClient;
    private final ObjectMapper objectMapper;

    public MultimediaBFFService(
    	MultimediaClient multimediaClient,
        ImageClient imageClient,
        ObjectMapper objectMapper
    ) {
        this.multimediaClient = multimediaClient;
        this.imageClient = imageClient;
        this.objectMapper = objectMapper;
    }

    public MovieDTO createWithImage(String jsonData, MultipartFile file) throws Exception {
        // 1) parse JSON
        CreateMovieWithImageRequest req =
            objectMapper.readValue(jsonData, CreateMovieWithImageRequest.class);
        // 2) subir imagen
        String url = imageClient.upload(file).url();
        // 3) llamar a movie-service
        CreateMovieRequest wrapped = new CreateMovieRequest(
            req.tipoMultimediaID(),
            req.titulo(),
            url,
            req.descripcion()
        );
        return multimediaClient.create(wrapped);
    }

    public MovieDTO updateWithImage(UpdateMovieWithImageRequest req, MultipartFile file) {
        ImageUploadResponse imgResp = imageClient.upload(file);
        String url = imgResp.url();
        System.out.print(url);
        CreateMovieRequest wrapped = new CreateMovieRequest(
            req.tipoMultimediaID(),
            req.titulo(),
            url,
            req.descripcion()
        );

        // 3) Llamar a movie-service pasando el ID desde el DTO
        return multimediaClient.update(req.multimediaID(), wrapped);
    }

    // Passthrough a lista y detalle si los necesitas
    public List<MultimediaDTO> listAll() {
        return multimediaClient.findAll();
    }
    public MultimediaDTO findById(Integer id) {
        return multimediaClient.get(id);
    }
}
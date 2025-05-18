package com.prueba.bbf.dto;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateCharacterWithCastAndImageRequest(
	    @NotBlank String nombre,
	    @NotBlank String descripcion,
	    List<CreateCastingItem> casting
	) {
	    public static record CreateCastingItem(
	        Integer multimediaID,
	        @NotBlank String rol,
	        @NotBlank String descripcion
	    ) {}
	}
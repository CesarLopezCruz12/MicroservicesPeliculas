package com.prueba.bbf.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record UpdateCharacterWithCastAndImageRequest(
    @NotNull Integer personajeID,
    @NotBlank String nombre,
    @NotBlank String descripcion,
    @Valid List<CreateCharacterWithCastAndImageRequest.CreateCastingItem> casting
) {}
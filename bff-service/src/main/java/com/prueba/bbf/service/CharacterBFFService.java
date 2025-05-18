package com.prueba.bbf.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.prueba.bbf.client.CastingClient;
import com.prueba.bbf.client.CharacterClient;
import com.prueba.bbf.client.ImageClient;
import com.prueba.bbf.dto.CharacterDTO;
import com.prueba.bbf.dto.CreateCastingRequest;
import com.prueba.bbf.dto.CreateCharacterCastingRequest;
import com.prueba.bbf.dto.CreateCharacterRequest;
import com.prueba.bbf.dto.CreateCharacterWithCastAndImageRequest;
import com.prueba.bbf.dto.ImageUploadResponse;
import com.prueba.bbf.dto.UpdateCharacterWithCastAndImageRequest;
import com.prueba.bbf.dto.UpdateCharacterWithCastRequest;

@Service
public class CharacterBFFService {

  private final CharacterClient characterClient;
  private final CastingClient castingClient;
  private final ImageClient   imageClient;

  public CharacterBFFService(CharacterClient characterClient,
		  					CastingClient castingClient,
		  					ImageClient imageClient) {
    this.characterClient = characterClient;
    this.castingClient   = castingClient;
    this.imageClient     = imageClient;
    
  }

  public CharacterDTO createPersonaje(CreateCharacterRequest req) {
    return characterClient.create(req);
  }
  
  public CharacterDTO createPersonajeWithCast(CreateCharacterCastingRequest req) {
      CharacterDTO creado = characterClient.create(
          new CreateCharacterRequest(
              req.nombre(),
              req.imagen(),
              req.descripcion()
          )
      );
      
      Integer personajeId = creado.personajeID();
      for (var castReq : req.casting()) {
    	  castingClient.create(
              new CreateCastingRequest(
                  castReq.multimediaID(),
                  personajeId,
                  castReq.rol(),
                  castReq.descripcion()
              )
          );
      }
      return creado;
  }
  
  public CharacterDTO updateCharacterWithCast(UpdateCharacterWithCastRequest req) {
      CharacterDTO updated = characterClient.update(
          req.personajeID(),
          new CreateCharacterRequest(
              req.nombre(),
              req.imagen(),
              req.descripcion()
          )
      );


      castingClient.deleteByPersonaje(req.personajeID());

  
      for (CreateCastingRequest castReq : req.casting()) {
          castingClient.create(
              new CreateCastingRequest(
                  castReq.multimediaID(),
                  req.personajeID(),
                  castReq.rol(),
                  castReq.descripcion()
              )
          );
      }

      return updated;
  }
  
  public CharacterDTO createPersonajeWithCastAndImage(
	        CreateCharacterWithCastAndImageRequest req,
	        MultipartFile file
	    ) {
	        // 1) subir imagen y obtener URL
	        ImageUploadResponse imgResp = imageClient.upload(file);
	        String imageUrl = imgResp.url();

	        // 2) crear personaje con la URL devuelta
	        CharacterDTO creado = characterClient.create(
	            new CreateCharacterRequest(
	                req.nombre(),
	                imageUrl,
	                req.descripcion()
	            )
	        );
	        Integer personajeId = creado.personajeID();

	        // 3) crear cada entrada de reparto
	        for (var item : req.casting()) {
	            castingClient.create(
	                new CreateCastingRequest(
	                    item.multimediaID(),
	                    personajeId,
	                    item.rol(),
	                    item.descripcion()
	                )
	            );
	        }

	        // 4) devolver el CharacterDTO resultante (incluye la URL en 'imagen')
	        return creado;
	    }

  public CharacterDTO updatePersonajeWithCastAndImage(
	        UpdateCharacterWithCastAndImageRequest req,
	        MultipartFile file
	    ) {
	        // 1) Subir la nueva imagen y obtener URL
	        ImageUploadResponse imgResp = imageClient.upload(file);
	        String imageUrl = imgResp.url();

	        // 2) Actualizar el personaje con la nueva URL
	        CharacterDTO updated = characterClient.update(
	            req.personajeID(),
	            new CreateCharacterRequest(
	                req.nombre(),
	                imageUrl,
	                req.descripcion()
	            )
	        );

	        // 3) Borrar reparto existente
	        castingClient.deleteByPersonaje(req.personajeID());

	        // 4) Volver a crear las entradas de reparto
	        for (var item : req.casting()) {
	            castingClient.create(
	                new CreateCastingRequest(
	                    item.multimediaID(),
	                    req.personajeID(),
	                    item.rol(),
	                    item.descripcion()
	                )
	            );
	        }

	        return updated;
	    }
}
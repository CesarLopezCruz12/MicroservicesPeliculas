package com.prueba.bbf.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.prueba.bbf.config.FeignMultipartConfig;
import com.prueba.bbf.dto.ImageUploadResponse;

@FeignClient(
	    name = "image-service",
	    url = "${services.image.url}",
	    configuration = FeignMultipartConfig.class
	)
	public interface ImageClient {
	    @PostMapping(value = "/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	    ImageUploadResponse upload(@RequestPart("file") MultipartFile file);
	}
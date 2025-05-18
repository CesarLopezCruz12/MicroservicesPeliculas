package com.prueba.image.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.prueba.image.service.ImageStorageService;


@RestController
@RequestMapping("/images")
public class ImageController {

  private final ImageStorageService storage;

  public ImageController(ImageStorageService storage) {
    this.storage = storage;
  }

  @PostMapping
  public ResponseEntity<Map<String,String>> upload(@RequestPart("file") MultipartFile file) {
    String url = storage.upload(file);
    return ResponseEntity.ok(Map.of("url", url));
  }
}
package com.prueba.image.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.core.sync.RequestBody;

@Service
public class ImageStorageService {

  private final S3Client s3;
  private final String bucket;
  private final String publicBaseUrl;

  public ImageStorageService(S3Client s3,
                             @Value("${cloudflare.r2.bucket}") String bucket,
                             @Value("${cloudflare.r2.public-base-url}") String publicBaseUrl) {
    this.s3 = s3;
    this.bucket = bucket;
    this.publicBaseUrl = publicBaseUrl;
  }

  public String upload(MultipartFile file) {
    String key = System.currentTimeMillis() + "-" + UUID.randomUUID() + 
                 "." + FilenameUtils.getExtension(file.getOriginalFilename());

    try (InputStream in = file.getInputStream()) {
      s3.putObject(
        PutObjectRequest.builder()
          .bucket(bucket)
          .key(key)
          .contentType(file.getContentType())
          .acl(ObjectCannedACL.PUBLIC_READ)
          .build(),
        RequestBody.fromInputStream(in, file.getSize())
      );
    } catch (IOException e) {
      throw new UncheckedIOException("Error subiendo imagen", e);
    }

    // construimos la URL pública:
    return String.format("%s/%s", publicBaseUrl, key);
  }
}

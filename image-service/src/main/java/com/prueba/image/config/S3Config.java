package com.prueba.image.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;

import java.net.URI;
@Configuration
public class S3Config {

  @Value("${cloudflare.r2.access-key}")  private String accessKey;
  @Value("${cloudflare.r2.secret-key}")  private String secretKey;
  @Value("${cloudflare.r2.region}")      private String region;
  @Value("${cloudflare.r2.endpoint}")    private String endpoint;

  @Bean
  public S3Client s3Client() {
    // HTTP client URLConnection (ya viene con aws-sdk-url-connection-client)
    UrlConnectionHttpClient httpClient = (UrlConnectionHttpClient) UrlConnectionHttpClient.builder().build();

    AwsBasicCredentials creds = AwsBasicCredentials.create(accessKey, secretKey);

    return S3Client.builder()
      .httpClient(httpClient)
      .credentialsProvider(StaticCredentialsProvider.create(creds))
      .region(Region.of(region))
      .endpointOverride(URI.create(endpoint))
      .serviceConfiguration(
         S3Configuration.builder()
           .pathStyleAccessEnabled(true) // R2 usa path-style
           .build()
      )
      .build();
  }
}
package com.prueba.character.exception;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String,String>> handleResponseStatusException(
            ResponseStatusException ex,
            WebRequest request) {

        // Construye un JSON con tu campo "message"
        Map<String,String> body = Map.of(
            "message", ex.getReason()
        );
        return new ResponseEntity<>(body, ex.getStatusCode());
    }
}

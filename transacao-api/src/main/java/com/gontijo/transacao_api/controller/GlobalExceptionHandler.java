package com.gontijo.transacao_api.controller;

import com.gontijo.transacao_api.infrastructure.exceptions.UnprocessableEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

// Tratamento de Erros com GlobalExceptionHandler

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UnprocessableEntity.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<String> handlerUnprocessableEntity(UnprocessableEntity e) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Erro: " + e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handlerUnprocessableEntity(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
    }
}

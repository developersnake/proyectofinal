package com.exacom.proyectofinal.exception;

import com.exacom.proyectofinal.domain.ExceptionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("Ha ocurrido un error inesperado", e);
        var list = new ArrayList<String>();
        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
            list.add("La propiedad " + fieldError.getField() + " " + fieldError.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(list);
    }

    @ExceptionHandler(CoreException.class)
    public ResponseEntity<ExceptionDTO> handleCoreException(CoreException e) {
        log.error("Ha ocurrido un error inesperado", e);
        var error = ExceptionDTO.builder()
                .userMessage(e.getUserMessage())
                .developerMessage(e.getDeveloperMessage())
                .errorCode(e.getErrorCode())
                .errorType(e.getErrorType())
                .build();
        return ResponseEntity.status(e.getErrorCode()).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> handleException(Exception e) {
        log.error("Ha ocurrido un error inesperado", e);
        var error = ExceptionDTO.builder()
                .userMessage("Ha ocurrido un error inesperado")
                .developerMessage(e.getMessage())
                .errorCode(500)
                .errorType("Internal Server Error")
                .build();
        return ResponseEntity.status(500).body(error);
    }

}

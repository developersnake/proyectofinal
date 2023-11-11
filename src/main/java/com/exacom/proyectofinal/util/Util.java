package com.exacom.proyectofinal.util;

import com.exacom.proyectofinal.exception.CoreException;
import org.springframework.http.HttpStatus;

public class Util {
    public static CoreException getCoreException(String msg, String devMsg, HttpStatus status) {
        return CoreException.builder()
                .userMessage(msg)
                .developerMessage(devMsg)
                .errorCode(status.value())
                .errorType(status.name())
                .build();
    }
}

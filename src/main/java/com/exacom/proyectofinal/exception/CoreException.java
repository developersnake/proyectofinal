package com.exacom.proyectofinal.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoreException extends Exception {
    private String userMessage;
    private String developerMessage;
    private int errorCode;
    private String errorType;
}

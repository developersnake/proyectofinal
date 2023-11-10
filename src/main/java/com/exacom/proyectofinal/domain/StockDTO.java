package com.exacom.proyectofinal.domain;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockDTO {
    @NotNull(message = "{NotNull}")
    private Long autorId;
    private String autorName;
    @NotNull(message = "{NotNull}")
    private Long libroId;
    private String libroName;
    @NotNull(message = "{NotNull}")
    private Integer cantidad;
}

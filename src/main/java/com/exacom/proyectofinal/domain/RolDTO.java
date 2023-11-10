package com.exacom.proyectofinal.domain;

import com.exacom.proyectofinal.validation.CoreValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolDTO {
    @NotNull(message = "{NotNull}", groups = {CoreValidation.Update.class})
    @Null(message = "{Null}", groups = {CoreValidation.Create.class})
    private Long id;
    @NotBlank(message = "{NotBlank}", groups = {CoreValidation.Create.class, CoreValidation.Update.class})
    private String name;
}

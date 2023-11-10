package com.exacom.proyectofinal.domain;

import com.exacom.proyectofinal.util.CountryEnum;
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
public class AutorDTO {
    @NotNull(message = "{NotNull}", groups = {CoreValidation.Update.class})
    @Null(message = "{Null}", groups = {CoreValidation.Create.class})
    private Long id;
    @NotNull(message = "{NotNull}", groups = {CoreValidation.Create.class, CoreValidation.Update.class})
    private CountryEnum country;
    @NotBlank(message = "{NotBlank}", groups = {CoreValidation.Create.class, CoreValidation.Update.class})
    private String name;
}

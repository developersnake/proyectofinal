package com.exacom.proyectofinal.domain;

import com.exacom.proyectofinal.util.CountryEnum;
import com.exacom.proyectofinal.util.GenderBookEnum;
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
public class LibroDTO {
    @NotNull(message = "{NotNull}", groups = {CoreValidation.Update.class})
    @Null(message = "{Null}", groups = {CoreValidation.Create.class})
    private Long id;
    @NotNull(message = "{NotNull}", groups = {CoreValidation.Create.class, CoreValidation.Update.class})
    private GenderBookEnum gender;
    @NotBlank(message = "{NotBlank}", groups = {CoreValidation.Create.class, CoreValidation.Update.class})
    private String title;
    @NotBlank(message = "{NotBlank}", groups = {CoreValidation.Create.class, CoreValidation.Update.class})
    private String editorial;
    @NotNull(message = "{NotNull}", groups = {CoreValidation.Create.class, CoreValidation.Update.class})
    private AutorDTO autor;
    @NotBlank(message = "{NotBlank}", groups = {CoreValidation.Create.class, CoreValidation.Update.class})
    private String isbn;
}

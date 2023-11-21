package com.exacom.proyectofinal.controller;

import com.exacom.proyectofinal.domain.UserDTO;
import com.exacom.proyectofinal.exception.CoreException;
import com.exacom.proyectofinal.service.UserService;
import com.exacom.proyectofinal.validation.CoreValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/v1/api")
@RequiredArgsConstructor
@Tag(name = "User", description = "User API")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Método para crear un usuario",
            description = "Método para crear un usuario en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario creado correctamente.",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "El usuario no existe en la base de datos.",
                    content = @Content(schema = @Schema(implementation = CoreException.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.",
                    content = @Content(schema = @Schema(implementation = CoreException.class)))
    })
    @PutMapping(value = "/create", produces = "application/json", consumes = "application/json")
    public String createUser(@Validated(CoreValidation.Create.class) @RequestBody UserDTO userDTO)
            throws CoreException {
        return userService.save(userDTO);
    }


    @Operation(summary = "Método para actualizar un usuario",
            description = "Método para actualizar un usuario en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizar correctamente.",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "El usuario no existe en la base de datos.",
                    content = @Content(schema = @Schema(implementation = CoreException.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.",
                    content = @Content(schema = @Schema(implementation = CoreException.class)))
    })
    @PostMapping(value = "/update", produces = "application/json", consumes = "application/json")
    public String updateUser(@Validated(CoreValidation.Update.class) @RequestBody UserDTO userDTO)
            throws CoreException {
        return userService.update(userDTO);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO) {
        return userService.login(userDTO.getUsername(), userDTO.getPassword());
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id) {
        return userService.delete(id);
    }

    @GetMapping("/findById/{id}")
    public UserDTO findById(@PathVariable(name = "id") Long id) throws CoreException {
        return userService.findById(id);
    }

    @GetMapping("/findByEmail/{email}")
    public UserDTO findByEmail(@PathVariable(name = "email") String email) throws CoreException {
        return userService.findByEmail(email);
    }

    @GetMapping("/findAll")
    public List<UserDTO> findAll() {
        return userService.findAll();
    }
}

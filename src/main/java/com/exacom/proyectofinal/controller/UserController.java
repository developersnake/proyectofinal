package com.exacom.proyectofinal.controller;

import com.exacom.proyectofinal.domain.UserDTO;
import com.exacom.proyectofinal.exception.CoreException;
import com.exacom.proyectofinal.service.UserService;
import com.exacom.proyectofinal.validation.CoreValidation;
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
public class UserController {

    private final UserService userService;

    @PutMapping("/create")
    public String createUser(@Validated(CoreValidation.Create.class) @RequestBody UserDTO userDTO)
            throws CoreException {
        return userService.save(userDTO);
    }

    @PostMapping("/update")
    public String updateUser(@Validated(CoreValidation.Update.class) @RequestBody UserDTO userDTO)
            throws CoreException {
        return userService.update(userDTO);
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

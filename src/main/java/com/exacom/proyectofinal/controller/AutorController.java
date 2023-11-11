package com.exacom.proyectofinal.controller;

import com.exacom.proyectofinal.domain.AutorDTO;
import com.exacom.proyectofinal.exception.CoreException;
import com.exacom.proyectofinal.service.AutorService;
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
@RequestMapping("/autor/v1/api")
@RequiredArgsConstructor
public class AutorController {
    private final AutorService autorService;

    @GetMapping("/findAll")
    public List<AutorDTO> findAll() {
        return autorService.findAll();
    }

    @GetMapping("/findById/{id}")
    public AutorDTO findById(@PathVariable("id") Long id) throws CoreException {
        return autorService.findById(id);
    }

    @PutMapping("/create")
    public String create(@Validated({CoreValidation.Create.class}) @RequestBody AutorDTO autorDTO) throws CoreException {
        return autorService.save(autorDTO);
    }

    @PostMapping(path = "/update")
    public String update(@Validated({CoreValidation.Update.class}) @RequestBody AutorDTO autorDTO) throws CoreException {
        return autorService.update(autorDTO);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        return autorService.delete(id);
    }
}

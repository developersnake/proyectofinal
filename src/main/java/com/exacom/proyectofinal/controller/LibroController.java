package com.exacom.proyectofinal.controller;

import com.exacom.proyectofinal.domain.LibroDTO;
import com.exacom.proyectofinal.exception.CoreException;
import com.exacom.proyectofinal.service.LibroService;
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
@RequestMapping("/libro/v1/api")
@RequiredArgsConstructor
public class LibroController {
    private final LibroService libroService;

    @GetMapping("/findAll")
    public List<LibroDTO> findAll() {
        return libroService.findAll();
    }

    @GetMapping("/findById/{id}")
    public LibroDTO findById(@PathVariable("id") Long id) throws CoreException {
        return libroService.findById(id);
    }

    @PutMapping("/create")
    public String create(@Validated({CoreValidation.Create.class}) @RequestBody LibroDTO libroDTO) throws CoreException {
        return libroService.save(libroDTO);
    }

    @PostMapping(path = "/update")
    public String update(@Validated({CoreValidation.Update.class}) @RequestBody LibroDTO libroDTO) throws CoreException {
        return libroService.update(libroDTO);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        return libroService.delete(id);
    }
}

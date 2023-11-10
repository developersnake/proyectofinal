package com.exacom.proyectofinal.controller;

import com.exacom.proyectofinal.domain.RolDTO;
import com.exacom.proyectofinal.exception.CoreException;
import com.exacom.proyectofinal.service.RolService;
import com.exacom.proyectofinal.validation.CoreValidation;
import jakarta.validation.Valid;
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
@RequestMapping("/rol/v1/api")
@RequiredArgsConstructor
public class RolController {
    private final RolService rolService;

    @GetMapping("/findAll")
    public List<RolDTO> findAll() {
        return rolService.findAll();
    }

    @GetMapping("/findById/{id}")
    public RolDTO findById(@PathVariable("id") Long id) throws CoreException {
        return rolService.findById(id);
    }

    @PutMapping("/create")
    public String create(@Validated({CoreValidation.Create.class}) @RequestBody RolDTO rolDTO) throws CoreException {
        return rolService.save(rolDTO);
    }

    @PostMapping(path = "/update", headers = "Content-Type=application/json; charset=UTF-8")
    public String update(@Validated({CoreValidation.Update.class}) @RequestBody RolDTO rolDTO) throws CoreException {
        return rolService.update(rolDTO);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        return rolService.delete(id);
    }
}

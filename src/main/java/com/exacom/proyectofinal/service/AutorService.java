package com.exacom.proyectofinal.service;

import com.exacom.proyectofinal.domain.AutorDTO;
import com.exacom.proyectofinal.exception.CoreException;

import java.util.List;

public interface AutorService {
    AutorDTO findById(Long id) throws CoreException;
    List<AutorDTO> findAll();
    String save(AutorDTO AutorDTO) throws CoreException;
    String update(AutorDTO AutorDTO) throws CoreException;
    String delete(Long id);
}

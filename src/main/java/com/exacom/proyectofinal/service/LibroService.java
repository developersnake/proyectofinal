package com.exacom.proyectofinal.service;

import com.exacom.proyectofinal.domain.LibroDTO;
import com.exacom.proyectofinal.exception.CoreException;

import java.util.List;

public interface LibroService {
    LibroDTO findById(Long id) throws CoreException;
    List<LibroDTO> findAll();
    String save(LibroDTO LibroDTO) throws CoreException;
    String update(LibroDTO LibroDTO) throws CoreException;
    String delete(Long id);
}

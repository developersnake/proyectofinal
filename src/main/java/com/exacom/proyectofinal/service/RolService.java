package com.exacom.proyectofinal.service;

import com.exacom.proyectofinal.domain.RolDTO;
import com.exacom.proyectofinal.exception.CoreException;

import java.util.List;

public interface RolService {
    RolDTO findById(Long id) throws CoreException;
    List<RolDTO> findAll();
    String save(RolDTO rolDTO) throws CoreException;
    String update(RolDTO rolDTO) throws CoreException;
    String delete(Long id);
}

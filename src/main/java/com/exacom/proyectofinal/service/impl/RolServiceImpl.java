package com.exacom.proyectofinal.service.impl;

import com.exacom.proyectofinal.domain.RolDTO;
import com.exacom.proyectofinal.exception.CoreException;
import com.exacom.proyectofinal.mapper.RolMapper;
import com.exacom.proyectofinal.repository.RolRepository;
import com.exacom.proyectofinal.service.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.exacom.proyectofinal.util.Util.getCoreException;

@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;
    private final RolMapper rolMapper;

    @Override
    public RolDTO findById(Long id) throws CoreException {
        var rol = rolRepository.findById(id).orElseThrow(() -> getCoreException("No se encontró el rol con id:" + id,
                "No se encontró el rol con el id especificado", HttpStatus.NOT_FOUND));
        return rolMapper.toDTO(rol);
    }

    @Override
    public List<RolDTO> findAll() {
        return rolRepository.findAll().stream().map(rolMapper::toDTO).toList();
    }

    @Override
    public String save(RolDTO rolDTO) throws CoreException {
        if(rolRepository.existsByName(rolDTO.getName())) {
            throw getCoreException("El rol con nombre: " + rolDTO.getName() + " ya existe.",
                    "El rol con el nombre especificado ya existe.", HttpStatus.CONFLICT);
        }
        var rol = rolMapper.toEntity(rolDTO);
        rol = rolRepository.save(rol);
        return "Rol guardado con id: " + rol.getId() + " y nombre: " + rol.getName() + ".";
    }

    @Override
    public String update(RolDTO rolDTO) throws CoreException {
        if(rolRepository.countByName(rolDTO.getName(), rolDTO.getId()) == 1) {
            throw getCoreException("El rol con nombre: " + rolDTO.getName() + " ya existe.",
                    "El rol con el nombre especificado ya existe.", HttpStatus.CONFLICT);
        }
        var rol = rolMapper.toEntity(rolDTO);
        rol = rolRepository.save(rol);
        return "Rol actualizado con id: " + rol.getId() + " y nombre: " + rol.getName() + ".";
    }

    @Override
    public String delete(Long id) {
        rolRepository.deleteById(id);
        return "Rol eliminado con id: " + id + ".";
    }
}

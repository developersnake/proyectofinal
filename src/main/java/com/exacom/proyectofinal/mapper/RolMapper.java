package com.exacom.proyectofinal.mapper;

import com.exacom.proyectofinal.domain.RolDTO;
import com.exacom.proyectofinal.model.Rol;
import org.springframework.stereotype.Component;

@Component
public class RolMapper implements StandartMapper<Rol, RolDTO> {

    @Override
    public Rol toEntity(RolDTO dto) {
        return Rol.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

    @Override
    public RolDTO toDTO(Rol entity) {
        return RolDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}

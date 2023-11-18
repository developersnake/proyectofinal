package com.exacom.proyectofinal.mapper;

import com.exacom.proyectofinal.domain.RolDTO;
import com.exacom.proyectofinal.domain.UserDTO;
import com.exacom.proyectofinal.model.Rol;
import com.exacom.proyectofinal.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class UserMapper implements StandartMapper<User, UserDTO> {

    private final RolMapper rolMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User toEntity(UserDTO dto) {
        var roles = new ArrayList<Rol>();
        if(dto.getRoles() != null) {
            dto.getRoles().stream().map(rolMapper::toEntity).forEach(roles::add);
        }
        return User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .roles(roles)
                .enabled(dto.isEnabled())
                .build();
    }

    @Override
    public UserDTO toDTO(User entity) {
        var roles = new ArrayList<RolDTO>();
        if(entity.getRoles() != null) {
            entity.getRoles().stream().map(rolMapper::toDTO).forEach(roles::add);
        }
        return UserDTO.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .email(entity.getEmail())
                .roles(roles)
                .enabled(entity.isEnabled())
                .build();
    }
}

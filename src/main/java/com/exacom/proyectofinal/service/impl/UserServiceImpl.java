package com.exacom.proyectofinal.service.impl;

import com.exacom.proyectofinal.domain.UserDTO;
import com.exacom.proyectofinal.exception.CoreException;
import com.exacom.proyectofinal.mapper.UserMapper;
import com.exacom.proyectofinal.repository.UserRepository;
import com.exacom.proyectofinal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.exacom.proyectofinal.util.Util.getCoreException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO findById(Long id) throws CoreException {
        var user = userRepository.findById(id)
                .orElseThrow(() -> getCoreException("No se encontró el usuario con el id: " + id,
                        "No se encontró registro en la base de datos.", HttpStatus.NOT_FOUND));
        return userMapper.toDTO(user);
    }

    @Override
    public UserDTO findByEmail(String email) throws CoreException {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> getCoreException("No se encontró el usuario con el email: " + email,
                        "No se encontró registro en la base de datos.", HttpStatus.NOT_FOUND));
        return userMapper.toDTO(user);
    }

    @Override
    public List<UserDTO> findAll() {
        var users = userRepository.findAll();
        return users.stream().map(userMapper::toDTO).toList();
    }

    @Override
    public String save(UserDTO userDTO) throws CoreException {
        if(userRepository.existsByEmail(userDTO.getEmail())){
            throw getCoreException("El email ya existe en la base de datos",
                    "El email ya existe en la base de datos", HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsByUsername(userDTO.getUsername())){
            throw getCoreException("El username ya existe en la base de datos",
                    "El username ya existe en la base de datos", HttpStatus.BAD_REQUEST);
        }
        var user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);
        return "Se creo el usuario con el id: " + user.getId();
    }

    @Override
    public String update(UserDTO userDTO) throws CoreException {
        if(userRepository.countByEmail(userDTO.getEmail(), userDTO.getId()) == 1){
            throw getCoreException("El email ya existe en la base de datos",
                    "El email ya existe en la base de datos", HttpStatus.BAD_REQUEST);
        }
        if(userRepository.countByUsername(userDTO.getUsername(), userDTO.getId()) == 1){
            throw getCoreException("El username ya existe en la base de datos",
                    "El username ya existe en la base de datos", HttpStatus.BAD_REQUEST);
        }
        var user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);
        return "Se actualizó el usuario con el id: " + user.getId();
    }

    @Override
    public String delete(Long id) {
        userRepository.deleteById(id);
        return "Se elimino el usuario con el id: " + id + " correctamente";
    }
}

package com.exacom.proyectofinal.service.impl;

import com.exacom.proyectofinal.domain.AutorDTO;
import com.exacom.proyectofinal.exception.CoreException;
import com.exacom.proyectofinal.mapper.AutorMapper;
import com.exacom.proyectofinal.repository.AutorRepository;
import com.exacom.proyectofinal.service.AutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.exacom.proyectofinal.util.Util.getCoreException;

@Service
@RequiredArgsConstructor
public class AutorServiceImpl implements AutorService {

    private final AutorRepository autorRepository;
    private final AutorMapper autorMapper;

    @Override
    public AutorDTO findById(Long id) throws CoreException {
        var autor = autorRepository.findById(id).orElseThrow(() -> getCoreException("No se encontró el Autor con id:" + id,
                "No se encontró el Autor con el id especificado", HttpStatus.NOT_FOUND));
        return autorMapper.toDTO(autor);
    }

    @Override
    public List<AutorDTO> findAll() {
        return autorRepository.findAll().stream().map(autorMapper::toDTO).toList();
    }

    @Override
    public String save(AutorDTO autorDTO) throws CoreException {
        var autor = autorMapper.toEntity(autorDTO);
        autor = autorRepository.save(autor);
        return "Autor guardado con id: " + autor.getId() + " y nombre: " + autor.getName() + ".";
    }

    @Override
    public String update(AutorDTO autorDTO) throws CoreException {
        var autor = autorMapper.toEntity(autorDTO);
        autor = autorRepository.save(autor);
        return "Autor actualizado con id: " + autor.getId() + " y nombre: " + autor.getName() + ".";
    }

    @Override
    public String delete(Long id) {
        autorRepository.deleteById(id);
        return "Autor eliminado con id: " + id + ".";
    }
}

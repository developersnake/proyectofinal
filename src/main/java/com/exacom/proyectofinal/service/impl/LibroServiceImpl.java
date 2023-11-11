package com.exacom.proyectofinal.service.impl;

import com.exacom.proyectofinal.domain.LibroDTO;
import com.exacom.proyectofinal.exception.CoreException;
import com.exacom.proyectofinal.mapper.LibroMapper;
import com.exacom.proyectofinal.repository.LibroRepository;
import com.exacom.proyectofinal.service.LibroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.exacom.proyectofinal.util.Util.getCoreException;

@Service
@RequiredArgsConstructor
public class LibroServiceImpl implements LibroService {

    private final LibroRepository libroRepository;
    private final LibroMapper libroMapper;

    @Override
    public LibroDTO findById(Long id) throws CoreException {
        var libro = libroRepository.findById(id).orElseThrow(() -> getCoreException("No se encontró el libro con id:" + id,
                "No se encontró el libro con el id especificado", HttpStatus.NOT_FOUND));
        return libroMapper.toDTO(libro);
    }

    @Override
    public List<LibroDTO> findAll() {
        return libroRepository.findAll().stream().map(libroMapper::toDTO).toList();
    }

    @Override
    public String save(LibroDTO libroDTO) throws CoreException {
        if(libroRepository.existsByIsbn(libroDTO.getIsbn())) {
            throw getCoreException("Ya existe un libro con el isbn: " + libroDTO.getIsbn(),
                    "Ya existe un libro con el isbn especificado", HttpStatus.BAD_REQUEST);
        }
        var libro = libroMapper.toEntity(libroDTO);
        libro = libroRepository.save(libro);
        return "Libro guardado con id: " + libro.getId() + " y nombre: " + libro.getTitle() + ".";
    }

    @Override
    public String update(LibroDTO libroDTO) throws CoreException {
        if(libroRepository.countByIsbnAndIdNot(libroDTO.getIsbn(), libroDTO.getId()) > 0) {
            throw getCoreException("Ya existe un libro con el isbn: " + libroDTO.getIsbn(),
                    "Ya existe un libro con el isbn especificado", HttpStatus.BAD_REQUEST);
        }
        var libro = libroMapper.toEntity(libroDTO);
        libro = libroRepository.save(libro);
        return "Libro actualizado con id: " + libro.getId() + " y nombre: " + libro.getTitle() + ".";
    }

    @Override
    public String delete(Long id) {
        libroRepository.deleteById(id);
        return "Libro eliminado con id: " + id + ".";
    }
}

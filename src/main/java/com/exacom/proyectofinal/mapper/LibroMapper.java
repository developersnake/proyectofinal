package com.exacom.proyectofinal.mapper;

import com.exacom.proyectofinal.domain.LibroDTO;
import com.exacom.proyectofinal.model.Libro;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LibroMapper implements StandartMapper<Libro, LibroDTO> {

    private final AutorMapper autorMapper;

    @Override
    public Libro toEntity(LibroDTO dto) {
        return Libro.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .autor(autorMapper.toEntity(dto.getAutor()))
                .editorial(dto.getEditorial())
                .gender(dto.getGender())
                .isbn(dto.getIsbn())
                .build();
    }

    @Override
    public LibroDTO toDTO(Libro entity) {
        return LibroDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .autor(autorMapper.toDTO(entity.getAutor()))
                .editorial(entity.getEditorial())
                .gender(entity.getGender())
                .isbn(entity.getIsbn())
                .build();
    }
}

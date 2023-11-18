package com.exacom.proyectofinal.service.impl;

import com.exacom.proyectofinal.domain.AutorDTO;
import com.exacom.proyectofinal.exception.CoreException;
import com.exacom.proyectofinal.mapper.AutorMapper;
import com.exacom.proyectofinal.model.Autor;
import com.exacom.proyectofinal.repository.AutorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AutorServiceImplTest {

    @Mock
    private AutorRepository autorRepository;

    @Mock
    private AutorMapper autorMapper;

    @InjectMocks
    private AutorServiceImpl autorService;

    @Test
    @DisplayName("Buscar Autor por id")
    void findById() throws CoreException {

        when(autorRepository.findById(anyLong())).thenReturn(Optional.of(getAutor()));
        when(autorMapper.toDTO(any(Autor.class))).thenReturn(getAutorDTO());

        var autorDTO = autorService.findById(1L);

        assertNotNull(autorDTO);
        assertEquals(1L, autorDTO.getId());
        assertEquals("Autor 1", autorDTO.getName());

        verify(autorRepository, times(1)).findById(anyLong());
        verify(autorMapper, times(1)).toDTO(any(Autor.class));

    }

    @Test
    @DisplayName("Buscar Autor por id, no encontrado")
    void findByIdWithNoFound() {
        try {
            when(autorRepository.findById(anyLong())).thenReturn(Optional.empty());
            autorService.findById(1L);
        } catch (CoreException ex) {
            assertEquals("No se encontró el Autor con id:1", ex.getUserMessage());
            assertEquals("No se encontró el Autor con el id especificado", ex.getDeveloperMessage());
        }
    }

    @Test
    @DisplayName("Buscar todos los Autores")
    void findAll() {

        when(autorRepository.findAll()).thenReturn(List.of(getAutor()));
        when(autorMapper.toDTO(any(Autor.class))).thenReturn(getAutorDTO());

        var autoresDTOList = autorService.findAll();

        assertNotNull(autoresDTOList);
        assertEquals(1, autoresDTOList.size());
        assertEquals(1L, autoresDTOList.get(0).getId());
        assertEquals("Autor 1", autoresDTOList.get(0).getName());

    }

    @Test
    @DisplayName("Guardar Autor")
    void save() throws CoreException {

        when(autorRepository.save(any(Autor.class))).thenReturn(getAutor());
        when(autorMapper.toEntity(any(AutorDTO.class))).thenReturn(getAutor());

        var result = autorService.save(getAutorDTO());

        assertNotNull(result);
        assertEquals("Autor guardado con id: 1 y nombre: Autor 1.", result);
    }

    @Test
    @DisplayName("Actualizar Autor")
    void update() throws CoreException {

        when(autorRepository.save(any(Autor.class))).thenReturn(getAutor());
        when(autorMapper.toEntity(any(AutorDTO.class))).thenReturn(getAutor());

        var result = autorService.update(getAutorDTO());

        assertNotNull(result);
        assertEquals("Autor actualizado con id: 1 y nombre: Autor 1.", result);
    }

    @Test
    @DisplayName("Eliminar Autor")
    void delete() {
        doNothing().when(autorRepository).deleteById(anyLong());

        var result = autorService.delete(1L);

        assertNotNull(result);
        assertEquals("Autor eliminado con id: 1.", result);
    }

    private Autor getAutor() {
        return Autor.builder()
                .id(1L)
                .name("Autor 1")
                .build();
    }

    private AutorDTO getAutorDTO() {
        return AutorDTO.builder()
                .id(1L)
                .name("Autor 1")
                .build();
    }
}
package com.exacom.proyectofinal.mapper;

import com.exacom.proyectofinal.domain.AutorDTO;
import com.exacom.proyectofinal.domain.LibroDTO;
import com.exacom.proyectofinal.model.Autor;
import com.exacom.proyectofinal.model.Libro;
import com.exacom.proyectofinal.util.CountryEnum;
import com.exacom.proyectofinal.util.GenderBookEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("LibroMapper Tests")
public class LibroMapperTest {

    @Mock
    private AutorMapper autorMapper;

    @InjectMocks
    private LibroMapper libroMapper;

    @Test
    @DisplayName("Test toEntity - Valid LibroDTO")
    public void testToEntityValidLibroDTO() {
        AutorDTO autorDTO = AutorDTO.builder().id(1L).name("Autor Test").country(CountryEnum.COLOMBIA).build();
        Autor autor = Autor.builder().id(1L).name("Autor Test").country(CountryEnum.COLOMBIA).build();
        LibroDTO libroDTO = LibroDTO.builder().id(1L).title("Title Test").autor(autorDTO).editorial("Editorial Test")
                .gender(GenderBookEnum.ACCION).isbn("ISBN Test").build();
        Libro expectedLibro = Libro.builder().id(1L).title("Title Test").autor(autor).editorial("Editorial Test")
                .gender(GenderBookEnum.ACCION).isbn("ISBN Test").build();

        when(autorMapper.toEntity(autorDTO)).thenReturn(autor);

        Libro result = libroMapper.toEntity(libroDTO);

        assertEquals(expectedLibro.getId(), result.getId());
        assertEquals(expectedLibro.getTitle(), result.getTitle());
        assertEquals(expectedLibro.getAutor(), result.getAutor());
        assertEquals(expectedLibro.getEditorial(), result.getEditorial());
        assertEquals(expectedLibro.getGender(), result.getGender());
        assertEquals(expectedLibro.getIsbn(), result.getIsbn());

    }

    @Test
    @DisplayName("Test toDTO - Valid Libro")
    public void testToDTOValidLibro() {
        AutorDTO expectedAutorDTO = AutorDTO.builder().id(1L).name("Autor Test").country(CountryEnum.ECUADOR).build();
        Autor autor = Autor.builder().id(1L).name("Autor Test").country(CountryEnum.COLOMBIA).build();
        Libro libro = Libro.builder().id(1L).title("Title Test").autor(autor).editorial("Editorial Test")
                .gender(GenderBookEnum.ACCION).isbn("ISBN Test").build();
        LibroDTO expectedLibroDTO = LibroDTO.builder().id(1L).title("Title Test").autor(expectedAutorDTO)
                .editorial("Editorial Test").gender(GenderBookEnum.ACCION).isbn("ISBN Test").build();

        when(autorMapper.toDTO(autor)).thenReturn(expectedAutorDTO);

        LibroDTO result = libroMapper.toDTO(libro);

        assertEquals(expectedLibroDTO, result);
    }
}
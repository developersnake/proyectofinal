package com.exacom.proyectofinal.mapper;

import com.exacom.proyectofinal.domain.AutorDTO;
import com.exacom.proyectofinal.model.Autor;
import com.exacom.proyectofinal.util.CountryEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AutorMapperTest {

    private AutorMapper autorMapper;

    @BeforeEach
    public void setUp() {
        autorMapper = new AutorMapper();
    }

    @Test
    @DisplayName("Test toEntity() - When valid DTO, then return valid entity")
    public void testToEntityWhenValidDtoThenReturnValidEntity() {
        // Arrange
        AutorDTO autorDTO = AutorDTO.builder()
                .id(1L)
                .name("John Doe")
                .country(CountryEnum.COLOMBIA)
                .build();

        // Act
        Autor autor = autorMapper.toEntity(autorDTO);

        // Assert
        assertEquals(autorDTO.getId(), autor.getId());
        assertEquals(autorDTO.getName(), autor.getName());
        assertEquals(autorDTO.getCountry(), autor.getCountry());
    }

    @Test
    @DisplayName("Test toEntity() - When null DTO, then return entity with null fields")
    public void testToEntityWhenNullDtoThenReturnEntityWithNullFields() {
        // Arrange
        AutorDTO autorDTO = new AutorDTO();

        // Act
        Autor autor = autorMapper.toEntity(autorDTO);

        // Assert
        assertNull(autor.getId());
        assertNull(autor.getName());
        assertNull(autor.getCountry());
    }

    @Test
    @DisplayName("Test toDTO() - When valid entity, then return valid DTO")
    public void testToDtoWhenValidEntityThenReturnValidDto() {
        // Arrange
        Autor autor = Autor.builder()
                .id(1L)
                .name("John Doe")
                .country(CountryEnum.COLOMBIA)
                .build();

        // Act
        AutorDTO autorDTO = autorMapper.toDTO(autor);

        // Assert
        assertEquals(autor.getId(), autorDTO.getId());
        assertEquals(autor.getName(), autorDTO.getName());
        assertEquals(autor.getCountry(), autorDTO.getCountry());
    }

    @Test
    @DisplayName("Test toDTO() - When null entity, then return DTO with null fields")
    public void testToDtoWhenNullEntityThenReturnDtoWithNullFields() {
        // Arrange
        Autor autor = new Autor();

        // Act
        AutorDTO autorDTO = autorMapper.toDTO(autor);

        // Assert
        assertNull(autorDTO.getId());
        assertNull(autorDTO.getName());
        assertNull(autorDTO.getCountry());
    }
}
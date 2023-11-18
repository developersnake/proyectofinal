package com.exacom.proyectofinal.controller;

import com.exacom.proyectofinal.domain.AutorDTO;
import com.exacom.proyectofinal.service.AutorService;
import com.exacom.proyectofinal.util.CountryEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AutorControllerTest {

    private static final String BASE_URL = "/autor/v1/api";

    private MockMvc mockMvc;

    @Mock
    private AutorService autorService;

    @InjectMocks
    private AutorController autorController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(autorController).build();
    }

    @Test
    @DisplayName("Obtener todos los autores")
    void findAll() throws Exception {

        when(autorService.findAll()).thenReturn(getAutorDtoList());

        mockMvc.perform(get(BASE_URL + "/findAll").contentType("application/json")
                .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0].name").value("Autor 1"))
                .andReturn().getResponse();

    }

    @Test
    @DisplayName("Obtener autor por id")
    void findById() throws Exception {

        when(autorService.findById(anyLong())).thenReturn(getAutorDtoList().get(0));

        mockMvc.perform(get(BASE_URL + "/findById/1").contentType("application/json")
                        .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Autor 1"))
                .andReturn().getResponse();
    }

    @Test
    @DisplayName("Crear autor")
    void create() throws Exception {

        var jsonMapper = new ObjectMapper();
        var autorDto = getAutorDtoList().get(0);
        autorDto.setId(null);
        var autorDtoJson = jsonMapper.writeValueAsString(autorDto);

        when(autorService.save(any(AutorDTO.class))).thenReturn("Autor creado");

        mockMvc.perform(put(BASE_URL + "/create").contentType("application/json")
                        .accept("application/json").content(autorDtoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Autor creado"))
                .andReturn().getResponse();
    }

    @Test
    @DisplayName("Actualizar autor")
    void update() throws Exception {

        var jsonMapper = new ObjectMapper();
        var autorDto = getAutorDtoList().get(0);
        var autorDtoJson = jsonMapper.writeValueAsString(autorDto);

        when(autorService.update(any(AutorDTO.class))).thenReturn("Autor creado");

        mockMvc.perform(post(BASE_URL + "/update").contentType("application/json")
                        .accept("application/json").content(autorDtoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Autor creado"))
                .andReturn().getResponse();
    }

    @Test
    @DisplayName("Eliminar autor")
    void delete() throws Exception {

        when(autorService.delete(anyLong())).thenReturn("Autor eliminado");

        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL + "/delete/1").contentType("application/json")
                        .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Autor eliminado"))
                .andReturn().getResponse();
    }

    private List<AutorDTO> getAutorDtoList() {
        var autorDtoList = new ArrayList<AutorDTO>();
        autorDtoList.add(AutorDTO.builder().id(1L).name("Autor 1").country(CountryEnum.COLOMBIA).build());
        autorDtoList.add(AutorDTO.builder().id(2L).name("Autor 2").country(CountryEnum.ARGENTINA).build());
        autorDtoList.add(AutorDTO.builder().id(3L).name("Autor 3").country(CountryEnum.MEXICO).build());
        return autorDtoList;
    }
}
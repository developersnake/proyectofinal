package com.exacom.proyectofinal.service;

import com.exacom.proyectofinal.domain.StockDTO;
import com.exacom.proyectofinal.exception.CoreException;

import java.util.List;

public interface StockService {
    StockDTO findById(Long autorId, Long libroId) throws CoreException;
    List<StockDTO> findAll();
    List<StockDTO> findAllByAutorId(Long autorId);
    List<StockDTO> findAllByLibroId(Long libroId);
    StockDTO save(StockDTO stockDTO);
    StockDTO update(StockDTO stockDTO);
    void delete(Long autorId, Long libroId);
}

package com.exacom.proyectofinal.controller;

import com.exacom.proyectofinal.domain.StockDTO;
import com.exacom.proyectofinal.exception.CoreException;
import com.exacom.proyectofinal.service.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stock/v1/api")
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    @GetMapping("/findAll")
    public List<StockDTO> findAll() {
        return stockService.findAll();
    }

    @GetMapping("/findById/{autorId}/{libroId}")
    public StockDTO findById(@PathVariable("autorId") Long autorId, @PathVariable("libroId") Long libroId)
            throws CoreException {
        return stockService.findById(autorId, libroId);
    }

    @GetMapping("/findByAutorId/{autorId}")
    public List<StockDTO> findByAutorId(@PathVariable("autorId") Long autorId) {
        return stockService.findAllByAutorId(autorId);
    }

    @GetMapping("/findByLibroId/{libroId}")
    public List<StockDTO> findByLibroId(@PathVariable("libroId") Long libroId) {
        return stockService.findAllByLibroId(libroId);
    }

    @PutMapping("/create")
    public StockDTO create(@Valid @RequestBody StockDTO stockDTO) throws CoreException {
        return stockService.save(stockDTO);
    }

    @PostMapping(path = "/update")
    public StockDTO update(@Valid @RequestBody StockDTO stockDTO) throws CoreException {
        return stockService.update(stockDTO);
    }

    @DeleteMapping("/delete/{autorId}/{libroId}")
    public String delete(@PathVariable("autorId") Long autorId, @PathVariable("libroId") Long libroId) {
        stockService.delete(autorId, libroId);
        return "Stock eliminado correctamente";
    }
}

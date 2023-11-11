package com.exacom.proyectofinal.service.impl;

import com.exacom.proyectofinal.domain.StockDTO;
import com.exacom.proyectofinal.exception.CoreException;
import com.exacom.proyectofinal.mapper.StockMapper;
import com.exacom.proyectofinal.model.Autor;
import com.exacom.proyectofinal.model.Libro;
import com.exacom.proyectofinal.model.pk.StockPK;
import com.exacom.proyectofinal.repository.StockRepository;
import com.exacom.proyectofinal.service.StockService;
import com.exacom.proyectofinal.util.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    @Override
    public StockDTO findById(Long autorId, Long libroId) throws CoreException {
        var stock = stockRepository.findById(getStockPK(autorId, libroId))
                .orElseThrow(() -> Util.getCoreException("No se encontró registros con los datos proporcionados",
                        "No hay registros en la base de datos con los datos proporcionados", HttpStatus.NOT_FOUND));
        log.info("Stock encontrado: {}", stock);
        return stockMapper.toDTO(stock);
    }

    @Override
    public List<StockDTO> findAll() {
        var stocks = stockRepository.findAll();
        return stocks.stream().map(stockMapper::toDTO).toList();
    }

    @Override
    public List<StockDTO> findAllByAutorId(Long autorId) {
        var stocks = stockRepository.findAllByAutor(autorId);
        return stocks.stream().map(stockMapper::toDTO).toList();
    }

    @Override
    public List<StockDTO> findAllByLibroId(Long libroId) {
        var stocks = stockRepository.findAllByLibro(libroId);
        return stocks.stream().map(stockMapper::toDTO).toList();
    }

    @Override
    public StockDTO save(StockDTO stockDTO) {
        var stock = stockMapper.toEntity(stockDTO);
        return stockMapper.toDTO(stockRepository.save(stock));
    }

    @Override
    public StockDTO update(StockDTO stockDTO) {
        return save(stockDTO);
    }

    @Override
    public void delete(Long autorId, Long libroId) {
        stockRepository.deleteById(getStockPK(autorId, libroId));
    }

    private StockPK getStockPK(Long autorId, Long libroId) {
        var stockPk = new StockPK();
        stockPk.setAutor(Autor.builder().id(autorId).build());
        stockPk.setLibro(Libro.builder().id(libroId).build());
        return stockPk;
    }
}

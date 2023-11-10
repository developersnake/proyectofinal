package com.exacom.proyectofinal.repository;

import com.exacom.proyectofinal.model.Stock;
import com.exacom.proyectofinal.model.pk.StockPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, StockPK> {
    @Query("FROM Stock s WHERE s.id.autor.id = :autorId")
    List<Stock> findAllByAutor(@Param("autorId") Long autorId);
    //@Query("FROM Stock s WHERE s.id.libro.id = :libroId")
    @Query(value = "SELECT * FROM stock s WHERE s.libro_id = :libroId", nativeQuery = true)
    List<Stock> findAllByLibro(@Param("libroId") Long libroId);
}

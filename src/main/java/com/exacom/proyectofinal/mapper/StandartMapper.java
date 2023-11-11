package com.exacom.proyectofinal.mapper;

/**
 * Interfaz para mapear entidades a DTOs y viceversa
 * @param <T> Objeto de la entidad
 * @param <R> Objeto del DTO
 */
public interface StandartMapper<T, R> {

    /**
     * Método para transformar una entidad a un DTO
     * @param dto Objeto para transformar a entidad
     * @return Entidad transformada
     */
    T toEntity(R dto);

    /**
     * Método para transformar una entidad a un DTO
     * @param entity Objeto para transformar a DTO
     * @return DTO transformado
     */
    R toDTO(T entity);
}

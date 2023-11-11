package com.exacom.proyectofinal.repository;

import com.exacom.proyectofinal.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RolRepository extends JpaRepository<Rol, Long> {

    boolean existsByName(String name);

    @Query("SELECT COUNT(r) FROM Rol r WHERE r.name = :name AND r.id <> :id")
    int countByName(@Param("name") String name, @Param("id") Long id);
}

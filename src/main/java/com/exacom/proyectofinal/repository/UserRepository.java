package com.exacom.proyectofinal.repository;

import com.exacom.proyectofinal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    @Query("SELECT COUNT(u) FROM User u WHERE u.email = :email AND u.id <> :id")
    int countByEmail(@Param("email") String email, @Param("id") Long id);

    @Query("SELECT COUNT(u) FROM User u WHERE u.username = :username AND u.id <> :id")
    int countByUsername(@Param("username") String username, @Param("id") Long id);
}

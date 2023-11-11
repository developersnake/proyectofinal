package com.exacom.proyectofinal.model;

import com.exacom.proyectofinal.util.CountryEnum;
import com.exacom.proyectofinal.util.GenderBookEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "libros")
@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 128)
    private String title;
    @Column(nullable = false, length = 64)
    private GenderBookEnum gender;
    @Column(nullable = false, length = 128)
    private String editorial;
    @ManyToOne
    private Autor autor;
    @Column(nullable = false, length = 64, unique = true)
    private String isbn;
}

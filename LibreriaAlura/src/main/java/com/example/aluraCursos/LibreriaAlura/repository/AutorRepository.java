package com.example.aluraCursos.LibreriaAlura.repository;

import com.example.aluraCursos.LibreriaAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombreContainsIgnoreCase(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.fechaNacimiento <= :fecha AND a.fechaDefunsion > :fecha")
    List<Autor> autorVivoEnCiertoAño(@Param("fecha") Long fecha);
}

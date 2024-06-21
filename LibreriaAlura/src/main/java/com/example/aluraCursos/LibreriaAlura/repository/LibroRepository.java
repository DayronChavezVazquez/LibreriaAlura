package com.example.aluraCursos.LibreriaAlura.repository;

import com.example.aluraCursos.LibreriaAlura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findByTitulo(String titulo);

    @Query("SELECT l FROM Autor a join a.libros l WHERE l.lenguajes = :idioma")
    List<Libro> findByLenguajes(String idioma);
}
//@Query(value = "SELECT l.* FROM libros l JOIN libro_lenguajes ll ON l.id = ll.libro_id WHERE ll.lenguaje = :lenguaje", nativeQuery = true)
//@Query(value = "SELECT * FROM book b WHERE b.languages LIKE %:language%", nativeQuery = true)
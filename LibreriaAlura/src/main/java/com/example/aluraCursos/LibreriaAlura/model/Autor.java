package com.example.aluraCursos.LibreriaAlura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    @Column(nullable = true)
    private Long fechaNacimiento;
    @Column(nullable = true)
    private Long fechaDefunsion;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;
    public Autor() {

    }
    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.fechaNacimiento = datosAutor.fechaNacimiento();
        this.fechaDefunsion = datosAutor.fechaDefunsion();

    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Long fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Long getFechaDefunsion() {
        return fechaDefunsion;
    }

    public void setFechaDefunsion(Long fechaDefunsion) {
        this.fechaDefunsion = fechaDefunsion;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return  "*************Autor******************"+ '\n' +
                "Nombre= " + nombre + '\n' +
                "FechaNacimiento= " + fechaNacimiento + '\n' +
                "FechaDefunsion= " + fechaDefunsion + '\n' +
                "*******************************";

    }
}

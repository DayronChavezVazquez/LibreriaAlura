package com.example.aluraCursos.LibreriaAlura.model;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name="libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
    private String titulo;
    @ManyToOne
    private Autor autor;
    private List<String> lenguajes;
    private long totalDeDescargas;


    public Libro() {
    }

    public Libro (DatosLibro datosLibro, Autor autor){
     this.titulo=datosLibro.titulo();
     this.autor=autor;
     this.lenguajes=datosLibro.lenguajes();
    this.totalDeDescargas=datosLibro.totalDedescargas();
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public List<String> getLenguajes() {
        return lenguajes;
    }

    public void setLenguajes(List<String> lenguajes) {
        this.lenguajes = lenguajes;
    }

    public long getTotalDeDescargas() {
        return totalDeDescargas;
    }

    public void setTotalDeDescargas(long totalDeDescargas) {
        this.totalDeDescargas = totalDeDescargas;
    }

    @Override
    public String toString() {
        return "*******************************"+ '\n' +
                "Titulo=" + titulo + '\n' +
                "Autor=" + autor.getNombre() +  '\n' +
                "Lenguajes=" + lenguajes +'\n' +
                "TotalDeDescargas=" + totalDeDescargas +'\n'+
                "********************************";
    }
}

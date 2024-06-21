package com.example.aluraCursos.LibreriaAlura.principal;

import com.example.aluraCursos.LibreriaAlura.model.*;
import com.example.aluraCursos.LibreriaAlura.repository.AutorRepository;
import com.example.aluraCursos.LibreriaAlura.repository.LibroRepository;
import com.example.aluraCursos.LibreriaAlura.service.ConsumoAPI;
import com.example.aluraCursos.LibreriaAlura.service.ConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado= new Scanner(System.in);
    private ConsumoAPI consumoAPI= new ConsumoAPI();
    private final  String URL_BASE= "https://gutendex.com/books/?";
    private ConvierteDatos conversor= new ConvierteDatos();
    private Optional<Libro> libroOptional;
    private Optional<Autor> autorOptional;
    private LibroRepository librorepository;
    private AutorRepository autorRepository;
    private List<Libro> libros;
    private List<Autor> autores;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
     this.librorepository=libroRepository;
     this.autorRepository=autorRepository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                1 - Buscar Libro por titulo
                2 - Listar libros registrados
                3 - Listar autores registrados 
                4 - Listar autores vivos en determinado año
                5 - Listar libros por idioma 
               
                           
                0 - Salir
                """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroWeb();
                    break;
                case 2:
                    mostrarLibrosRegistrados();
                    break;
                case 3:
                    mostrarAutoresRegistrados();
                    break;
                case 4:
                    AutoresVivosPorAño();
                    break;
                case 5:
                    litarLibrosPorIdioma();
                    break;

                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }
    private DatosLibro getDatosLibro() {
        System.out.println("Escribe el nombre del libro a buscar");
        var nombreLibro= teclado.nextLine();

        var json= consumoAPI.obtenerDatos(URL_BASE +"search="+ nombreLibro.replace(" ", "%20").toLowerCase());
        DatosGenerales datos= conversor.obtenerDatos(json, DatosGenerales.class);

        if(datos.results().isEmpty()){
            return null;
        }else {
            return datos.results().get(0);
        }
    }

    private  Autor getAutor(DatosLibro datosLibro){
        List<DatosAutor> datosAutores = datosLibro.autor();
        if (!datosAutores.isEmpty()){
            DatosAutor datosAutor = datosLibro.autor().get(0);
            Autor autor = new Autor(datosAutor);
            System.out.println("Autor: "+ autor.getNombre());
            return autor;
        }else {
            Autor autor = new Autor();
            System.out.println("Autor: " + autor.getNombre());
            return autor;
        }
    }

   private void guardadAutor(Autor autor){
       autorOptional = autorRepository.findByNombreContainsIgnoreCase(autor.getNombre());
       if(autorOptional.isPresent()){
      }else{
           autorRepository.save(autor);
      }
   }

    private void buscarLibroWeb(){
        DatosLibro datosLibro = getDatosLibro();//creamos la plantilla de datos para maniobrar
        if(datosLibro != null){

            libroOptional = librorepository.findByTitulo(datosLibro.titulo()); //traemos info de la base de datos
            if (libroOptional.isPresent()){
                System.out.println("Libro existente no hay necesidad de registrar");
            }else {
                Autor autor = getAutor(datosLibro);
                guardadAutor(autor);
                Libro libro = new Libro(datosLibro, autor);
                System.out.println(libro);
                librorepository.save(libro);
            }

        }else{
            System.out.println("libro no encontrado");
        }
    }

    private void mostrarLibrosRegistrados() {
   libros = librorepository.findAll();
   if (libros.isEmpty()){
       System.out.println("Aun no hay libros registrados");
   }else {
       libros.forEach(System.out::println);
   }

    }
    private void mostrarAutoresRegistrados() {
        autores=autorRepository.findAll();
        if (autores.isEmpty()){
            System.out.println("No se han registrado ningun autor");
        }else {
            autores.stream().forEach(System.out::println);
        }
    }

    private void AutoresVivosPorAño() {
        System.out.println("Indica el año que deseas consultar los autores vivos");
        Long año=teclado.nextLong();
        try {
            List<Autor> autoresVivos=autorRepository.autorVivoEnCiertoAño(año);
            if (autoresVivos.isEmpty()){
                System.out.println("No hay autores vivos en ese periodo");
                System.out.println("*****************************************");
            }else {
                autoresVivos.stream().forEach(System.out::println);
            }
        }catch (InputMismatchException e){
            System.out.println("Introduce un año valido");
            System.out.println("************************************");
        }

    }

    private void litarLibrosPorIdioma() {

        System.out.println(menuIdioma);
        int idioma = teclado.nextInt();
        String lenguaje= idiomaLibro(idioma);
        System.out.println("Idioma seleccionado: " + lenguaje);

        List<Libro> listaPoridioma=librorepository.findAll();

       listaPoridioma.stream().filter(l -> l.getLenguajes().contains(lenguaje)).forEach(System.out::println);

      }

    String menuIdioma= """
            Selecciona el idioma  que deseas buscar el libro
            1- Ingles
            2- Frances
            3- Portugues
            4- Español
            5- Ruso
            6- Italiano
            """;

    private String idiomaLibro(int idioma){
        switch (idioma){
            case 1:
                return "en";

            case 2:
                return "fr";

            case 3:
                return "pt";

            case 4:
                return "es";

            case 5:
                return "ru";

            case 6:
                return "it";

            default:
                System.out.println("Opción no valida");
        return null;
        }
}
}

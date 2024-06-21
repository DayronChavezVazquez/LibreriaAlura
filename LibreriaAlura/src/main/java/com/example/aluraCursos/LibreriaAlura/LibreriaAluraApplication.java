package com.example.aluraCursos.LibreriaAlura;

import com.example.aluraCursos.LibreriaAlura.principal.Principal;
import com.example.aluraCursos.LibreriaAlura.repository.AutorRepository;
import com.example.aluraCursos.LibreriaAlura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibreriaAluraApplication implements CommandLineRunner {
    @Autowired
	private LibroRepository libroRepository;
	@Autowired
	private AutorRepository autorRepository;
	public static void main(String[] args) {
		SpringApplication.run(LibreriaAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal=new Principal(libroRepository, autorRepository);
		principal.muestraElMenu();
	}
}

package com.example.aluraCursos.LibreriaAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosGenerales(
        @JsonAlias ("results") List<DatosLibro> results
        ) {

}

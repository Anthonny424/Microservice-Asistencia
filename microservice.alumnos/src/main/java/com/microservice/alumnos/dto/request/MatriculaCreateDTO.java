package com.microservice.alumnos.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data

public class MatriculaCreateDTO {
    @NotNull(message = "Debe proporcionar los datos del alumno")
    @Valid
    private AlumnoCreateDTO alumnoCreateDTO;
    @NotNull(message = "Debe especificar un grado válido")
    private Long idgrado;
}

package com.microservice.alumnos.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class CursoCreateDTO {
    @NotBlank(message = "El nombre del curso es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre del curso debe tener entre 3 y 50 caracteres")
    private String nombrecurso;
    @NotNull(message = "Debe especificar el grado")
    private Long idgrado;
    @NotNull(message = "Debe especificar el profesor")
    private Long idprofesor;
}

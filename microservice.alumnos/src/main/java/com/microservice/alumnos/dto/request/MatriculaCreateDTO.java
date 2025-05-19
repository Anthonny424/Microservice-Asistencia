package com.microservice.alumnos.dto.request;

import lombok.Data;

@Data

public class MatriculaCreateDTO {
    private AlumnoCreateDTO alumnoCreateDTO;
    private Long idgrado;

}

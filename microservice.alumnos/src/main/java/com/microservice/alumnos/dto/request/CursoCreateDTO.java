package com.microservice.alumnos.dto.request;

import lombok.Data;

@Data

public class CursoCreateDTO {
    private String nombrecurso;
    private Long idgrado;
    private Long idprofesor;
}

package com.microservice.alumnos.dto.request;

import lombok.Data;

@Data

public class RegistroCursoDTO {
    private Long idalumno;
    private Long idcurso;
    private Long idprofesor;
    private String estado;

}

package com.microservice.alumnos.dto.response;

import lombok.Data;

@Data

public class ProfesorResponseDTO {
    private Long idprofesor;
    private String nombre;
    private String apellido;
    private int dni;
    private int telefono;
    private String especialidad;
}

package com.microservice.alumnos.dto.response;

import lombok.Data;

@Data

public class AlumnoResponseDTO {
    private Long idalumno;
    private String nombre;
    private String apellido;
    private int dni;
    private String qr;
    private String foto;
    private Long idpadre;
}

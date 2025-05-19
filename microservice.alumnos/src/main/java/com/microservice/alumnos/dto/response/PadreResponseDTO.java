package com.microservice.alumnos.dto.response;

import lombok.Data;

@Data

public class PadreResponseDTO {
    private Long idpadre;
    private String nombre;
    private String apellido;
    private int dni;
    private String direccion;
    private Long telefono;
    private Long iduser;
}

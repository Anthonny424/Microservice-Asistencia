package com.microservice.alumnos.dto.request;

import lombok.Data;

@Data

public class PadreCreateDTO {
    private String nombre;
    private String apellido;
    private int dni;
    private String direccion;
    private Long telefono;
    private UserCreateDTO userCreateDTO;


}

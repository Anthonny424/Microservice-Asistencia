package com.microservice.alumnos.dto.request;

import lombok.Data;

@Data

public class ProfesorCreateDTO {
    private String nombre;
    private String apellido;
    private int dni;
    private int telefono;
    private String especialidad;
    private UserCreateDTO userCreateDTO;
}

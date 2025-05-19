package com.microservice.alumnos.dto.request;

import lombok.Data;

@Data

public class UserCreateDTO {
    private String correo;
    private String contraseña;
}

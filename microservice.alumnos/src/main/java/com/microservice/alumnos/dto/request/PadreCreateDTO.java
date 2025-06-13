package com.microservice.alumnos.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data

public class PadreCreateDTO {
    @NotBlank(message = "El nombre del padre no puede estar vacío")
    private String nombre;
    @NotBlank(message = "El apellido del padre no puede estar vacío")
    private String apellido;
    @Min(value = 10000000, message = "El DNI debe tener al menos 8 dígitos")
    @Max(value = 99999999, message = "El DNI debe tener como máximo 8 dígitos")
    private int dni;
    @NotBlank(message = "La dirección del padre no puede estar vacía")
    private String direccion;
    private Long telefono;
    @NotNull(message = "Debe especificarse un usuario")
    @Valid
    private UserCreateDTO userCreateDTO;
}

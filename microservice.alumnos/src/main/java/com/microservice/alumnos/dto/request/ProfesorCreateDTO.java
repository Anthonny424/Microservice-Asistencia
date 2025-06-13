package com.microservice.alumnos.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data

public class ProfesorCreateDTO {
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;
    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
    private String apellido;
    @Min(value = 10000000, message = "El DNI debe tener al menos 8 dígitos")
    @Max(value = 99999999, message = "El DNI no puede tener más de 8 dígitos")
    private int dni;
    @Min(value = 900000000, message = "El teléfono debe tener 9 dígitos")
    @Max(value = 999999999, message = "El teléfono debe tener 9 dígitos")
    private int telefono;
    @NotBlank(message = "La especialidad no puede estar vacía")
    private String especialidad;
    @NotNull(message = "Debe proporcionar las credenciales del usuario")
    @Valid
    private UserCreateDTO userCreateDTO;
}

package com.microservice.alumnos.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data

public class AlumnoCreateDTO{
        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
        private String nombre;
        @NotBlank(message = "El apellido no puede estar vacío")
        @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
        private String apellido;
        @Min(value = 10000000, message = "El DNI debe tener al menos 8 dígitos")
        @Max(value = 99999999, message = "El DNI debe tener como máximo 8 dígitos")
        private int dni;
        private String qr;
        private String foto;
        @NotNull(message = "Debe especificarse un padre o apoderado")
        @Valid
        private PadreCreateDTO padreCreateDTO;
}

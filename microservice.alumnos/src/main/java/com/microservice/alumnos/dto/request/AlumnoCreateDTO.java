package com.microservice.alumnos.dto.request;

import lombok.Data;

@Data

public class AlumnoCreateDTO{
        private String nombre;
        private String apellido;
        private int dni;
        private String qr;
        private String foto;
        private PadreCreateDTO padreCreateDTO;


}

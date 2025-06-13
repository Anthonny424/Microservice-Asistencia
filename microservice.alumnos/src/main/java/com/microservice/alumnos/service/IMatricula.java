package com.microservice.alumnos.service;

import com.microservice.alumnos.dto.request.MatriculaCreateDTO;
import com.microservice.alumnos.dto.response.CredencialesDTO;


public interface IMatricula {
    CredencialesDTO matricular(MatriculaCreateDTO matriculaCreateDTO, String foto, String qr);
}

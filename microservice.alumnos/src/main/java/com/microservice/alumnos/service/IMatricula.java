package com.microservice.alumnos.service;

import com.microservice.alumnos.dto.request.MatriculaCreateDTO;


public interface IMatricula {
    void matricular(MatriculaCreateDTO matriculaCreateDTO, String foto, String qr);
}

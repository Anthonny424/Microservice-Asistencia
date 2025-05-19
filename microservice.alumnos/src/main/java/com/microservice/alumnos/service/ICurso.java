package com.microservice.alumnos.service;

import com.microservice.alumnos.dto.request.CursoCreateDTO;

public interface ICurso {
    void crearCurso(CursoCreateDTO cursoCreateDTO);
}

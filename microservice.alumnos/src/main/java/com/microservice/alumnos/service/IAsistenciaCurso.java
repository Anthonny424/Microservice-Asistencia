package com.microservice.alumnos.service;

import com.microservice.alumnos.dto.request.RegistroCursoDTO;

import java.util.List;
import java.util.Map;

public interface IAsistenciaCurso {
    void registrarAlumnos(List<RegistroCursoDTO> list);
    Map<String, Object> obtenerResumenPorCurso(Long idAlumno, Long idCurso);
}

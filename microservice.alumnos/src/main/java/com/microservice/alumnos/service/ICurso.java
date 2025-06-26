package com.microservice.alumnos.service;

import com.microservice.alumnos.dto.request.CursoCreateDTO;
import com.microservice.alumnos.dto.response.CursoResponseDTO;
import com.microservice.alumnos.model.Curso;

import java.util.List;
import java.util.Map;

public interface ICurso {
    void crearCurso(CursoCreateDTO cursoCreateDTO);
    Curso buscarPorId(Long idcurso);
    List<CursoResponseDTO> buscarCursosPorProfesor(Long iduser);
    List<Map<String, Object>> listarCursosPorAlumno(Long idalumno);
}

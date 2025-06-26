package com.microservice.alumnos.service;

import com.microservice.alumnos.dto.response.AlumnoResponseDTO;
import com.microservice.alumnos.dto.response.AsistenciaGeneralDTO;
import com.microservice.alumnos.model.AsistenciaGeneral;

import java.util.List;
import java.util.Map;

public interface IAsistenciaGeneral {
    AlumnoResponseDTO registrarAsistencia(int dni);
    List<Map<String, Object>> obtenerResumenSemanal(Long idalumno);
    List<Map<String, Object>> obtenerResumen7Dias(Long idalumno);

}

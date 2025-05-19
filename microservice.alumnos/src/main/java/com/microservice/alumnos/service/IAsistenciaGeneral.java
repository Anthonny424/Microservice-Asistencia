package com.microservice.alumnos.service;

import com.microservice.alumnos.dto.response.AlumnoResponseDTO;
import com.microservice.alumnos.dto.response.AsistenciaGeneralDTO;
import com.microservice.alumnos.model.AsistenciaGeneral;

import java.util.List;

public interface IAsistenciaGeneral {
    AlumnoResponseDTO registrarAsistencia(int dni);


}

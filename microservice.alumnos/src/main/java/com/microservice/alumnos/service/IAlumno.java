package com.microservice.alumnos.service;


import com.microservice.alumnos.dto.response.AlumnoResponseDTO;
import com.microservice.alumnos.dto.response.CredencialesDTO;
import com.microservice.alumnos.model.Alumno;


import java.util.List;

public interface IAlumno {
    List<AlumnoResponseDTO> getAllAlumnos();
    Alumno findByDni(int dni);
    List<Alumno> alumnosquefaltaronhoy();
    String enviarSMSPadres(List<Alumno> alumnoList);
    List<AlumnoResponseDTO> buscarPorIdUser(Long iduser);
    List<AlumnoResponseDTO> buscarPorCurso(Long idcurso);
    Alumno buscarPorId(Long idalumno);
}

package com.microservice.alumnos.service;

import com.microservice.alumnos.dto.request.ProfesorCreateDTO;
import com.microservice.alumnos.dto.response.ProfesorResponseDTO;
import com.microservice.alumnos.model.Profesor;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProfesor {
    Profesor findById(Long id);
    void crearProfesor(ProfesorCreateDTO profesorCreateDTO);
    List<ProfesorResponseDTO> listarProfesores();
    ProfesorCreateDTO buscarporiduser(Long iduser);
    Long buscaridprofesor (Long iduser);
    void actualizarProfesor(ProfesorCreateDTO profesorCreateDTO);

}

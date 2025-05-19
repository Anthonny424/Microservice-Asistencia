package com.microservice.alumnos.service;

import com.microservice.alumnos.dto.response.GradoResponseDTO;
import com.microservice.alumnos.model.Grado;

import java.util.List;

public interface IGrado {
    List<GradoResponseDTO> listarGrados();
    Grado findById(Long id);
}

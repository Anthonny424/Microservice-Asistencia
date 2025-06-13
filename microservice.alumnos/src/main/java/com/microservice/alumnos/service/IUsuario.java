package com.microservice.alumnos.service;

import com.microservice.alumnos.dto.response.CredencialesDTO;
import com.microservice.alumnos.dto.response.UserResponseDTO;

import java.util.List;

public interface IUsuario {
    UserResponseDTO buscarPorId(Long id);
}

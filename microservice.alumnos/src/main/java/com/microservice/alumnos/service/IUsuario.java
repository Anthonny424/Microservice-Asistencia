package com.microservice.alumnos.service;

import com.microservice.alumnos.dto.response.UserResponseDTO;

public interface IUsuario {
    UserResponseDTO buscarPorId(Long id);
}

package com.microservice.alumnos.service;

import com.microservice.alumnos.dto.response.UserResponseDTO;
import com.microservice.alumnos.model.Usuario;
import com.microservice.alumnos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements IUsuario{

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserResponseDTO buscarPorId(Long id) {
        Usuario usuario = repository.findById(id).get();
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setCorreo(usuario.getCorreo());
        return userResponseDTO;
    }
}

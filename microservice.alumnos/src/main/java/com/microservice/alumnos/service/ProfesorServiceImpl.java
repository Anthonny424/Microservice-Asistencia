package com.microservice.alumnos.service;

import com.microservice.alumnos.dto.request.ProfesorCreateDTO;
import com.microservice.alumnos.dto.response.ProfesorResponseDTO;
import com.microservice.alumnos.model.Profesor;
import com.microservice.alumnos.model.Usuario;
import com.microservice.alumnos.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfesorServiceImpl implements IProfesor{

    @Autowired
    private ProfesorRepository repository;

    @Autowired
    private PasswordGeneratorService passwordGeneratorService;

    @Override
    public Profesor findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public void crearProfesor(ProfesorCreateDTO profesorCreateDTO) {
        Usuario usuario = new Usuario();
        usuario.setCorreo(profesorCreateDTO.getUserCreateDTO().getCorreo());
        usuario.setContraseña(passwordGeneratorService.generarContraseñaAleatoria());
        Profesor profesor = new Profesor();
        profesor.setNombre(profesorCreateDTO.getNombre());
        profesor.setApellido(profesorCreateDTO.getApellido());
        profesor.setDni(profesorCreateDTO.getDni());
        profesor.setTelefono(profesorCreateDTO.getTelefono());
        profesor.setEspecialidad(profesorCreateDTO.getEspecialidad());
        profesor.setUsuario(usuario);
        repository.save(profesor);
    }

    @Override
    public List<ProfesorResponseDTO> listarProfesores() {
        List<ProfesorResponseDTO> profesoresDTO = new ArrayList<>();
        List<Profesor> profesors = repository.findAll();
        for (Profesor x: profesors){
            ProfesorResponseDTO profesorResponseDTO = new ProfesorResponseDTO();
            profesorResponseDTO.setIdprofesor(x.getIdprofesor());
            profesorResponseDTO.setNombre(x.getNombre());
            profesorResponseDTO.setApellido(x.getApellido());
            profesorResponseDTO.setDni(x.getDni());
            profesorResponseDTO.setTelefono(x.getTelefono());
            profesorResponseDTO.setEspecialidad(x.getEspecialidad());
            profesoresDTO.add(profesorResponseDTO);
        }
        return profesoresDTO;
    }


}

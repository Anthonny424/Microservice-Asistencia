package com.microservice.alumnos.service;

import com.microservice.alumnos.dto.request.ProfesorCreateDTO;
import com.microservice.alumnos.dto.request.UserCreateDTO;
import com.microservice.alumnos.dto.response.ProfesorResponseDTO;
import com.microservice.alumnos.model.Profesor;
import com.microservice.alumnos.model.Rol;
import com.microservice.alumnos.model.Usuario;
import com.microservice.alumnos.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfesorServiceImpl implements IProfesor{

    @Autowired
    private ProfesorRepository repository;

    @Autowired
    private PasswordGeneratorService passwordGeneratorService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SmsSenderService smsSenderService;

    @Override
    public Profesor findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public void crearProfesor(ProfesorCreateDTO profesorCreateDTO) {
        String contraseña = passwordGeneratorService.generarContraseñaAleatoria();
        Usuario usuario = new Usuario();
        usuario.setCorreo(profesorCreateDTO.getUserCreateDTO().getCorreo());
        usuario.setContraseña(passwordEncoder.encode(contraseña));
        Profesor profesor = new Profesor();
        profesor.setNombre(profesorCreateDTO.getNombre());
        profesor.setApellido(profesorCreateDTO.getApellido());
        profesor.setDni(profesorCreateDTO.getDni());
        profesor.setTelefono(profesorCreateDTO.getTelefono());
        profesor.setEspecialidad(profesorCreateDTO.getEspecialidad());
        profesor.setUsuario(usuario);
        repository.save(profesor);
        smsSenderService.sendMessage("+51"+profesor.getTelefono(), "Bienvenido al sistema "+profesor.getNombre()+" "+profesor.getApellido()
        +". Sus credenciales son: "+profesor.getUsuario().getCorreo()+ " / "+contraseña+". Recuerde que debe cambiar su contraseña");
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

    @Override
    public ProfesorCreateDTO buscarporiduser(Long iduser) {
        ProfesorCreateDTO dto = new ProfesorCreateDTO();
        Profesor p = repository.findByUsuarioIduser(iduser);
        dto.setNombre(p.getNombre());
        dto.setApellido(p.getApellido());
        dto.setDni(p.getDni());
        dto.setTelefono(p.getTelefono());
        dto.setEspecialidad(p.getEspecialidad());
        UserCreateDTO u = new UserCreateDTO();
        u.setCorreo(p.getUsuario().getCorreo());
        u.setContraseña("");
        dto.setUserCreateDTO(u);
        return dto;
    }

    @Override
    public Long buscaridprofesor(Long iduser) {
        return repository.findByUsuarioIduser(iduser).getIdprofesor();
    }

    @Override
    public void actualizarProfesor(ProfesorCreateDTO profesorCreateDTO) {
        Profesor profesoractualizado = repository.findByDni(profesorCreateDTO.getDni());
        profesoractualizado.setTelefono(profesorCreateDTO.getTelefono());
        Usuario usuario = profesoractualizado.getUsuario();
        usuario.setIduser(usuario.getIduser());
        usuario.setRol(Rol.ROLE_PROFESOR);
        usuario.setContraseña(passwordEncoder.encode(profesorCreateDTO.getUserCreateDTO().getContraseña()));
        usuario.setCorreo(profesorCreateDTO.getUserCreateDTO().getCorreo());
        profesoractualizado.setUsuario(usuario);
        repository.save(profesoractualizado);
    }


}

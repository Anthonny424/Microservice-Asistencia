package com.microservice.alumnos.service;

import com.microservice.alumnos.dto.request.AlumnoCreateDTO;
import com.microservice.alumnos.dto.request.MatriculaCreateDTO;
import com.microservice.alumnos.dto.response.CredencialesDTO;
import com.microservice.alumnos.model.*;
import com.microservice.alumnos.repository.AlumnoRepository;
import com.microservice.alumnos.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.Date;

@Service
public class MatriculaServiceImpl implements IMatricula{

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private PasswordGeneratorService passwordGeneratorService;

    @Autowired
    private GradoServiceImpl gradoService;

    @Autowired
    private AlumnoServiceImpl alumnoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public CredencialesDTO matricular(MatriculaCreateDTO matriculaCreateDTO, String foto, String qr) {

        CredencialesDTO credencialesDTO = new CredencialesDTO();
        String contraseñagenerada = passwordGeneratorService.generarContraseñaAleatoria();

        Usuario usuario = new Usuario();
        usuario.setCorreo(matriculaCreateDTO.getAlumnoCreateDTO().getPadreCreateDTO().getUserCreateDTO().getCorreo());
        usuario.setContraseña(passwordEncoder.encode(contraseñagenerada));
        usuario.setRol(Rol.ROLE_USER);

        Padre padre = new Padre();
        padre.setNombre(matriculaCreateDTO.getAlumnoCreateDTO().getPadreCreateDTO().getNombre());
        padre.setApellido(matriculaCreateDTO.getAlumnoCreateDTO().getPadreCreateDTO().getApellido());
        padre.setDni(matriculaCreateDTO.getAlumnoCreateDTO().getPadreCreateDTO().getDni());
        padre.setDireccion(matriculaCreateDTO.getAlumnoCreateDTO().getPadreCreateDTO().getDireccion());
        padre.setTelefono(matriculaCreateDTO.getAlumnoCreateDTO().getPadreCreateDTO().getTelefono());
        padre.setUsuario(usuario);

        Alumno alumno= new Alumno();
        alumno.setNombre(matriculaCreateDTO.getAlumnoCreateDTO().getNombre());
        alumno.setApellido(matriculaCreateDTO.getAlumnoCreateDTO().getApellido());
        alumno.setDni(matriculaCreateDTO.getAlumnoCreateDTO().getDni());
        alumno.setQr(qr);
        alumno.setFoto(foto);
        alumno.setPadre(padre);

        Matricula matricula = new Matricula();
        int año = Year.now().getValue();
        matricula.setAñoelectivo(año);
        matricula.setFechamatricula(new Date());
        matricula.setEstado("Matriculado");
        matricula.setGrado(gradoService.findById(matriculaCreateDTO.getIdgrado()));
        matricula.setAlumno(alumno);

        matriculaRepository.save(matricula);
        credencialesDTO.setCorreo(usuario.getCorreo());
        credencialesDTO.setContraseña(contraseñagenerada);
        return credencialesDTO;
    }

}

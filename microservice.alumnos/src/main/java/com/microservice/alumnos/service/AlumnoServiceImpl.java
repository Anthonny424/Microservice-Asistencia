package com.microservice.alumnos.service;


import com.microservice.alumnos.dto.response.AlumnoResponseDTO;
import com.microservice.alumnos.model.Alumno;
import com.microservice.alumnos.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlumnoServiceImpl implements IAlumno{

    @Autowired
    private AlumnoRepository repository;

    @Autowired
    private SmsSenderService smsSenderService;


    @Override
    public List<AlumnoResponseDTO> getAllAlumnos() {
        List<Alumno> alumnos = repository.findAll();
        List<AlumnoResponseDTO> alumnoResponseDTOS = new ArrayList<>();
        for (Alumno x: alumnos){
            AlumnoResponseDTO alumnoResponseDTO = new AlumnoResponseDTO();
            alumnoResponseDTO.setIdalumno(x.getIdalumno());
            alumnoResponseDTO.setNombre(x.getNombre());
            alumnoResponseDTO.setApellido(x.getApellido());
            alumnoResponseDTO.setDni(x.getDni());
            alumnoResponseDTO.setQr(x.getQr());
            alumnoResponseDTO.setFoto(x.getFoto());
            alumnoResponseDTO.setIdpadre(x.getPadre().getIdpadre());
            alumnoResponseDTOS.add(alumnoResponseDTO);
        }
        return alumnoResponseDTOS;
    }


    @Override
    public Alumno findByDni(int dni) {
        return repository.findByDni(dni);
    }

    @Override
    public List<Alumno> alumnosquefaltaronhoy() {
        return repository.findAlumnosQueNoAsistieronHoy();
    }

    public String enviarSMSPadres(List<Alumno> alumnosquefaltaron){
        alumnosquefaltaron.stream().forEach(alumno ->  smsSenderService.sendMessage(String.valueOf("+" + alumno.getPadre().getTelefono()),
                "Notificación: Su hijo "+ alumno.getNombre() + " " + alumno.getApellido() + " ha faltado HOY. ¿Usted sabe sobre su falta?"));
        return "Alerta enviada!";
    }

    @Override
    public List<AlumnoResponseDTO> buscarPorIdUser(Long iduser) {
        List<Alumno> hijos = repository.findAllByPadreUsuarioIduser(iduser);
        List<AlumnoResponseDTO> hijosDTO = new ArrayList<>();
        for (Alumno x: hijos){
            AlumnoResponseDTO alumnoResponseDTO = new AlumnoResponseDTO();
            alumnoResponseDTO.setIdalumno(x.getIdalumno());
            alumnoResponseDTO.setNombre(x.getNombre());
            alumnoResponseDTO.setApellido(x.getApellido());
            alumnoResponseDTO.setDni(x.getDni());
            alumnoResponseDTO.setQr(x.getQr());
            alumnoResponseDTO.setFoto(x.getFoto());
            alumnoResponseDTO.setIdpadre(x.getPadre().getIdpadre());
            hijosDTO.add(alumnoResponseDTO);
        }
        return hijosDTO;
    }

}

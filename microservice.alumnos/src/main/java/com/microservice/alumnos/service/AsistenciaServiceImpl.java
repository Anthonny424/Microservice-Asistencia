package com.microservice.alumnos.service;

import com.microservice.alumnos.dto.response.AlumnoResponseDTO;
import com.microservice.alumnos.dto.response.AsistenciaGeneralDTO;
import com.microservice.alumnos.model.Alumno;
import com.microservice.alumnos.model.AsistenciaGeneral;
import com.microservice.alumnos.repository.AsistenciaGeneralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AsistenciaServiceImpl implements IAsistenciaGeneral{

    @Autowired
    private AsistenciaGeneralRepository repository;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private AlumnoServiceImpl alumnoService;

    @Override
    public AlumnoResponseDTO registrarAsistencia(int dni) {
        AsistenciaGeneral asistenciaGeneral = new AsistenciaGeneral();
        asistenciaGeneral.setFecharegistrada(new Date());
        asistenciaGeneral.setEstado("Asistido");
        Alumno alumno = alumnoService.findByDni(dni);
        asistenciaGeneral.setAlumno(alumno);
        repository.save(asistenciaGeneral);

        AlumnoResponseDTO alumnoResponseDTO = new AlumnoResponseDTO();
        alumnoResponseDTO.setIdalumno(alumno.getIdalumno());
        alumnoResponseDTO.setNombre(alumno.getNombre());
        alumnoResponseDTO.setApellido(alumno.getApellido());
        alumnoResponseDTO.setDni(alumno.getDni());
        alumnoResponseDTO.setQr(alumno.getQr());
        alumnoResponseDTO.setFoto(alumno.getFoto());
        alumnoResponseDTO.setIdpadre(alumno.getPadre().getIdpadre());
        return alumnoResponseDTO;
    }

}

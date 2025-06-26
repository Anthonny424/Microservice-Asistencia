package com.microservice.alumnos.service;

import com.microservice.alumnos.dto.response.AlumnoResponseDTO;
import com.microservice.alumnos.model.Alumno;
import com.microservice.alumnos.model.AsistenciaGeneral;
import com.microservice.alumnos.repository.AsistenciaGeneralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        Alumno alumno = alumnoService.findByDni(dni);

        int asistenciasHoy = repository.contarAsistenciasHoy(alumno.getIdalumno());
        if (asistenciasHoy > 0) {
            throw new IllegalStateException("El alumno ya ha registrado asistencia hoy.");
        }

        AsistenciaGeneral asistenciaGeneral = new AsistenciaGeneral();
        Date ahora = new Date();
        asistenciaGeneral.setFecharegistrada(ahora);

        Calendar calLimite = Calendar.getInstance();
        calLimite.setTime(ahora);
        calLimite.set(Calendar.HOUR_OF_DAY, 8);
        calLimite.set(Calendar.MINUTE, 0);
        calLimite.set(Calendar.SECOND, 0);
        calLimite.set(Calendar.MILLISECOND, 0);

        asistenciaGeneral.setEstado(ahora.after(calLimite.getTime()) ? "Tardanza" : "Asistido");
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

    @Override
    public List<Map<String, Object>> obtenerResumenSemanal(Long idalumno) {
        List<Object[]> resultados = repository.obtenerResumenSemanalAsistencia(idalumno);

        List<Map<String, Object>> respuesta = new ArrayList<>();

        for (Object[] fila : resultados) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("dia", fila[0]);
            map.put("hora", fila[1]);
            map.put("estado", fila[2]);
            respuesta.add(map);
        }

        return respuesta;
    }

    @Override
    public List<Map<String, Object>> obtenerResumen7Dias(Long idalumno) {
        List<Object[]> resultados = repository.resumenUltimos7Dias(idalumno);

        List<Map<String, Object>> lista = new ArrayList<>();
        for (Object[] fila : resultados) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("dia", fila[0]);
            map.put("hora", fila[1]);
            map.put("estado", fila[2]);
            lista.add(map);
        }
        return lista;
    }

}

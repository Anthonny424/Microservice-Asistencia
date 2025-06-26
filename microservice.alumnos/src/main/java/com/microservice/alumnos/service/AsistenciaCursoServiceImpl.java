package com.microservice.alumnos.service;

import com.microservice.alumnos.dto.request.RegistroCursoDTO;
import com.microservice.alumnos.model.AsistenciaCurso;
import com.microservice.alumnos.repository.AsistenciaCursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AsistenciaCursoServiceImpl implements IAsistenciaCurso{

    @Autowired
    private AsistenciaCursoRepository asistenciaCursoRepository;

    @Autowired
    private CursoServiceImpl cursoService;

    @Autowired
    private ProfesorServiceImpl profesorService;

    @Autowired
    private AlumnoServiceImpl alumnoService;

    @Override
    public void registrarAlumnos(List<RegistroCursoDTO> list) {
        for(RegistroCursoDTO x: list){
            AsistenciaCurso asistencia = new AsistenciaCurso();
            asistencia.setAlumno(alumnoService.buscarPorId(x.getIdalumno()));
            asistencia.setCurso(cursoService.buscarPorId(x.getIdcurso()));
            asistencia.setProfesor(profesorService.findById(x.getIdprofesor()));
            asistencia.setEstado(x.getEstado());
            asistencia.setFechaAsistencia(new Date());
            asistenciaCursoRepository.save(asistencia);
        }
    }

    @Override
    public Map<String, Object> obtenerResumenPorCurso(Long idAlumno, Long idCurso) {
        List<Object[]> resultado = asistenciaCursoRepository.obtenerResumenAsistencia(idAlumno, idCurso);
        if (resultado.isEmpty()) return null;

        Object[] fila = resultado.get(0);
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("porcentaje_asistencia", fila[0]);
        mapa.put("porcentaje_inasistencia", fila[1]);
        return mapa;
    }
}

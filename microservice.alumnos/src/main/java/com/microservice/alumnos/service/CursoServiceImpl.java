package com.microservice.alumnos.service;

import com.microservice.alumnos.dto.request.CursoCreateDTO;
import com.microservice.alumnos.dto.response.CursoResponseDTO;
import com.microservice.alumnos.model.Curso;
import com.microservice.alumnos.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class CursoServiceImpl implements ICurso{

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private GradoServiceImpl gradoService;

    @Autowired
    private ProfesorServiceImpl profesorService;

    @Override
    public void crearCurso(CursoCreateDTO cursoCreateDTO) {
        Curso curso = new Curso();
        curso.setNombrecurso(cursoCreateDTO.getNombrecurso());
        curso.setGrado(gradoService.findById(cursoCreateDTO.getIdgrado()));
        curso.setProfesor(profesorService.findById(cursoCreateDTO.getIdprofesor()));
        cursoRepository.save(curso);
    }

    @Override
    public Curso buscarPorId(Long idcurso) {
        return cursoRepository.findById(idcurso).get();
    }

    @Override
    public List<CursoResponseDTO> buscarCursosPorProfesor(Long iduser) {
        List<Curso> cursos =  cursoRepository.findByProfesorUsuarioIduser(iduser);
        List<CursoResponseDTO> cursosdto = new ArrayList<>();
        for(Curso x: cursos){
            CursoResponseDTO dto = new CursoResponseDTO();
            dto.setIdcurso(x.getIdcurso());
            dto.setNombrecurso(x.getNombrecurso());
            cursosdto.add(dto);
        }
        return cursosdto;
    }

    @Override
    public List<Map<String, Object>> listarCursosPorAlumno(Long idalumno) {
        List<Object[]> resultados = cursoRepository.obtenerCursosPorAlumno(idalumno);
        List<Map<String, Object>> lista = new ArrayList<>();

        for (Object[] fila : resultados) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("idcurso", fila[0]);
            map.put("nombrecurso", fila[1]);
            map.put("grado", fila[2]);
            String nombreCompleto = fila[3] + " " + fila[4];
            map.put("profesor", nombreCompleto);
            lista.add(map);
        }
        return lista;
    }
}

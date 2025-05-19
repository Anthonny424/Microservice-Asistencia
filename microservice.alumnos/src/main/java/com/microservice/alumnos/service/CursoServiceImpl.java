package com.microservice.alumnos.service;

import com.microservice.alumnos.dto.request.CursoCreateDTO;
import com.microservice.alumnos.model.Curso;
import com.microservice.alumnos.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

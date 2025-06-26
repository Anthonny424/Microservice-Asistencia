package com.microservice.alumnos.controller;

import com.microservice.alumnos.dto.request.ProfesorCreateDTO;
import com.microservice.alumnos.dto.request.RegistroCursoDTO;
import com.microservice.alumnos.dto.response.AlumnoResponseDTO;
import com.microservice.alumnos.dto.response.CursoResponseDTO;
import com.microservice.alumnos.service.AlumnoServiceImpl;
import com.microservice.alumnos.service.AsistenciaCursoServiceImpl;
import com.microservice.alumnos.service.CursoServiceImpl;
import com.microservice.alumnos.service.ProfesorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profe")
public class ProfesorController {

    @Autowired
    private AlumnoServiceImpl alumnoService;

    @Autowired
    private AsistenciaCursoServiceImpl asistenciaCursoService;

    @Autowired
    private CursoServiceImpl cursoService;

    @Autowired
    private ProfesorServiceImpl profesorService;


    @GetMapping("/verProfesor/{iduser}")
    public ResponseEntity<?> buscarProfesor(@PathVariable Long iduser){
        return ResponseEntity.status(HttpStatus.OK).body(profesorService.buscarporiduser(iduser));
    }

    @PutMapping("/updateProfe")
    public ResponseEntity<?> actualizarprofesor(@RequestBody ProfesorCreateDTO profesorCreateDTO){
        profesorService.actualizarProfesor(profesorCreateDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Updated!");
    }

    @GetMapping("/listarCursos/{iduser}")
    public ResponseEntity<List<CursoResponseDTO>> verCursosDelProfesor(@PathVariable Long iduser){
        return ResponseEntity.status(HttpStatus.OK).body(cursoService.buscarCursosPorProfesor(iduser));
    }

    @GetMapping("/verIdProfe/{iduser}")
    public ResponseEntity<Long> veridprofesor(@PathVariable Long iduser){
        return ResponseEntity.status(HttpStatus.OK).body(profesorService.buscaridprofesor(iduser));
    }


    @GetMapping("/listarPorCurso/{idcurso}")
    public ResponseEntity<List<AlumnoResponseDTO>> buscarAlumnosPorCurso(@PathVariable Long idcurso){
        return ResponseEntity.status(HttpStatus.OK).body(alumnoService.buscarPorCurso(idcurso));
    }

    @PostMapping("/registrarAsistencias")
    public ResponseEntity<?> registrarAsistencias (@RequestBody List<RegistroCursoDTO> list){
        asistenciaCursoService.registrarAlumnos(list);
        return ResponseEntity.status(HttpStatus.OK).body("Asistencias enviadas!");
    }


}

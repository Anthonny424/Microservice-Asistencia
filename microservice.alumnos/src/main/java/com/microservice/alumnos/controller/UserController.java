package com.microservice.alumnos.controller;

import com.microservice.alumnos.dto.request.*;
import com.microservice.alumnos.dto.response.*;
import com.microservice.alumnos.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private PadreServiceImpl padreService;

    @Autowired
    private AlumnoServiceImpl alumnoService;

    @Autowired
    private StorageServiceImpl storageService;

    @Autowired
    private SmsSenderService smsSenderService;

    @Autowired
    private AsistenciaCursoServiceImpl asistenciaCursoService;

    @Autowired
    private AsistenciaServiceImpl asistenciaService;

    @Autowired
    private CursoServiceImpl cursoService;

    @GetMapping("/media/{filename:.+}")
    public ResponseEntity<Resource> getfile(@PathVariable String filename) throws IOException {
        Resource file = storageService.loadAsResource(filename);
        String contentType = Files.probeContentType(file.getFile().toPath());
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, contentType).body(file);
    }

    @GetMapping("/verPadre/{iduser}")
    public ResponseEntity<PadreCreateDTO> buscarusuario(@PathVariable Long iduser){
        return ResponseEntity.status(HttpStatus.OK).body(padreService.buscarPorId(iduser));
    }

    @PutMapping("/updatePadre")
    public ResponseEntity<?> actualizarapadre(@Valid @RequestBody PadreCreateDTO padreCreateDTO){
        padreService.actualizarPadre(padreCreateDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Updated!");
    }

    @GetMapping("/listarAlumnos/{iduser}")
    public ResponseEntity<List<AlumnoResponseDTO>> veralumnos(@PathVariable Long iduser){
        return ResponseEntity.status(HttpStatus.OK).body(alumnoService.buscarPorIdUser(iduser));
    }

    @GetMapping("/verMisCursos/{idalumno}")
    public ResponseEntity<List<Map<String, Object>>> cursosPorAlumno(@PathVariable Long idalumno) {
        return ResponseEntity.ok(cursoService.listarCursosPorAlumno(idalumno));
    }

    @GetMapping("/resumenPorCurso/{idalumno}/{idcurso}")
    public ResponseEntity<?> getResumenPorPath(
            @PathVariable Long idalumno,
            @PathVariable Long idcurso
    ) {
        Map<String, Object> resumen = asistenciaCursoService.obtenerResumenPorCurso(idalumno, idcurso);
        return resumen == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(resumen);
    }

    @GetMapping("/resumenPorSemana/{idalumno}")
    public ResponseEntity<List<Map<String, Object>>> obtenerResumenSemanal(@PathVariable Long idalumno) {
        return ResponseEntity.ok(asistenciaService.obtenerResumenSemanal(idalumno));
    }

//    @GetMapping("/resumen7dias/{idalumno}")
//    public ResponseEntity<List<Map<String, Object>>> obtenerResumen7Dias(@PathVariable Long idalumno) {
//        List<Map<String, Object>> respuesta = asistenciaService.obtenerResumen7Dias(idalumno);
//        return ResponseEntity.ok(respuesta);
//    }

}

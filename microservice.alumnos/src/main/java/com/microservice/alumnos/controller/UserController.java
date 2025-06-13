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

    //Reportes pendiente

}

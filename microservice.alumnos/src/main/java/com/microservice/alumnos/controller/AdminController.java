package com.microservice.alumnos.controller;

import com.microservice.alumnos.dto.request.CursoCreateDTO;
import com.microservice.alumnos.dto.request.MatriculaCreateDTO;
import com.microservice.alumnos.dto.request.ProfesorCreateDTO;
import com.microservice.alumnos.dto.response.AlumnoResponseDTO;
import com.microservice.alumnos.dto.response.CredencialesDTO;
import com.microservice.alumnos.dto.response.GradoResponseDTO;
import com.microservice.alumnos.dto.response.ProfesorResponseDTO;
import com.microservice.alumnos.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final HttpServletRequest request;

    @Autowired
    private AsistenciaServiceImpl asistenciaService;

    @Autowired
    private AlumnoServiceImpl alumnoService;

    @Autowired
    private StorageServiceImpl storageService;

    @Autowired
    private QRCodeGeneratorService qrCodeGeneratorService;

    @Autowired
    private MatriculaServiceImpl matriculaService;

    @Autowired
    private ProfesorServiceImpl profesorService;

    @Autowired
    private GradoServiceImpl gradoService;

    @Autowired
    private CursoServiceImpl cursoService;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    public AdminController(HttpServletRequest request) {
        this.request = request;
    }


    @GetMapping("/asistencia/{dni}")
    public ResponseEntity<AlumnoResponseDTO> registrarAsistencia(@PathVariable int dni) throws IOException, URISyntaxException {
        AlumnoResponseDTO alumnoResponseDTO = asistenciaService.registrarAsistencia(dni);
        return ResponseEntity.status(HttpStatus.OK).body(alumnoResponseDTO);
    }

    @GetMapping("/cerrarAsistencias")
    public ResponseEntity<String> enviarAsistencias(){
        alumnoService.enviarSMSPadres(alumnoService.alumnosquefaltaronhoy());
        return ResponseEntity.status(HttpStatus.OK).body("Alertas enviadas!");
    }

    @PostMapping(value = "/matricular", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CredencialesDTO> crearProducto(@RequestPart("file") MultipartFile multipartFile, @RequestPart("matricula") @Valid MatriculaCreateDTO matriculaCreateDTO) throws IOException {

        String path = storageService.store(multipartFile);
        String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        String urlFoto = ServletUriComponentsBuilder.fromHttpUrl(host).path("/media/").path(path).toUriString();


        byte[] qrBytes = qrCodeGeneratorService.generateQrCodeImage(String.valueOf(matriculaCreateDTO.getAlumnoCreateDTO().getDni()),200, 200);
        String qrFilename = "qr_" + matriculaCreateDTO.getAlumnoCreateDTO().getDni() + ".png";
        storageService.store(qrBytes, qrFilename);
        String qrUrl = ServletUriComponentsBuilder.fromHttpUrl(host).path("/media/").path(qrFilename).toUriString();

        CredencialesDTO credencialesDTO = matriculaService.matricular(matriculaCreateDTO, urlFoto, qrUrl);
        return ResponseEntity.status(HttpStatus.OK).body(credencialesDTO);
    }

    @PostMapping("/createProfesor")
    public ResponseEntity<String> crearprofesor(@Valid @RequestBody ProfesorCreateDTO profesorCreateDTO){
        profesorService.crearProfesor(profesorCreateDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Created!");
    }

    @GetMapping("/listarGrados")
    public ResponseEntity<List<GradoResponseDTO>> vergrados(){
        return ResponseEntity.status(HttpStatus.OK).body(gradoService.listarGrados());
    }

    @GetMapping("/listarProfesores")
    public ResponseEntity<List<ProfesorResponseDTO>> verprofesores(){
        return ResponseEntity.status(HttpStatus.OK).body(profesorService.listarProfesores());
    }

    @PostMapping("/createCurso")
    public ResponseEntity<String> crearCurso(@Valid @RequestBody CursoCreateDTO cursoCreateDTO){
        cursoService.crearCurso(cursoCreateDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Created!");
    }

}

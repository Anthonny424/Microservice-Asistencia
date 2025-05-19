package com.microservice.alumnos.controller;

import com.microservice.alumnos.dto.request.*;
import com.microservice.alumnos.dto.response.*;
import com.microservice.alumnos.model.Alumno;
import com.microservice.alumnos.model.Padre;
import com.microservice.alumnos.model.Profesor;
import com.microservice.alumnos.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final HttpServletRequest request;

    public UserController(HttpServletRequest request) {
        this.request = request;
    }

    @Autowired
    private GradoServiceImpl gradoService;

    @Autowired
    private PadreServiceImpl padreService;

    @Autowired
    private QRCodeGeneratorService qrCodeGeneratorService;

    @Autowired
    private AlumnoServiceImpl alumnoService;

    @Autowired
    private StorageServiceImpl storageService;

    @Autowired
    private AsistenciaServiceImpl asistenciaService;

    @Autowired
    private SmsSenderService smsSenderService;

    @Autowired
    private ProfesorServiceImpl profesorService;

    @Autowired
    private CursoServiceImpl cursoService;

    @Autowired
    private MatriculaServiceImpl matriculaService;


//    LOGIM (FALTA)

//    NavAdmin:
//    Asistencia General (ALR)
//    Matricular Alumnos (ALR)
//    Insertar profesor (ALR)
//    Insertar curso (listar profesores y listar grado) ARL
//    LogOut (FALTA)

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

    @GetMapping("/media/{filename:.+}")
    public ResponseEntity<Resource> getfile(@PathVariable String filename) throws IOException {
        Resource file = storageService.loadAsResource(filename);
        String contentType = Files.probeContentType(file.getFile().toPath());
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, contentType).body(file);
    }

    @PostMapping(value = "/matricular", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> crearProducto(@RequestPart("file") MultipartFile multipartFile, @RequestPart("matricula") @Valid MatriculaCreateDTO matriculaCreateDTO) throws IOException {

        String path = storageService.store(multipartFile);
        String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        String urlFoto = ServletUriComponentsBuilder.fromHttpUrl(host).path("/media/").path(path).toUriString();


        byte[] qrBytes = qrCodeGeneratorService.generateQrCodeImage(String.valueOf(matriculaCreateDTO.getAlumnoCreateDTO().getDni()),200, 200);
        String qrFilename = "qr_" + matriculaCreateDTO.getAlumnoCreateDTO().getDni() + ".png";
        storageService.store(qrBytes, qrFilename);
        String qrUrl = ServletUriComponentsBuilder.fromHttpUrl(host).path("/media/").path(qrFilename).toUriString();

        matriculaService.matricular(matriculaCreateDTO, urlFoto, qrUrl);
        return ResponseEntity.status(HttpStatus.OK).body("Created!");
    }

    @PostMapping("/createProfesor")
    public ResponseEntity<String> crearprofesor(@RequestBody ProfesorCreateDTO profesorCreateDTO){
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
    public ResponseEntity<String> crearCurso(@RequestBody CursoCreateDTO cursoCreateDTO){
        cursoService.crearCurso(cursoCreateDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Created!");
    }




//    NavProfesor:
//    Perfil
//    AsistenciaCurso
//    LogOut




//    NavPadre:
//    Perfil /YA=
//    Reportes (FALTA)
//    Alumnos (YA)
//    LogOut


    @GetMapping("/verPadre/{iduser}")
    public ResponseEntity<PadreCreateDTO> buscarusuario(@PathVariable Long iduser){
        return ResponseEntity.status(HttpStatus.OK).body(padreService.buscarPorId(iduser));
    }

    @PostMapping("/updatePadre")
    public ResponseEntity<?> actualizarapadre(@RequestBody PadreCreateDTO padreCreateDTO){
        padreService.actualizarPadre(padreCreateDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Updated!");
    }

    @GetMapping("/listarAlumnos/{iduser}")
    public ResponseEntity<List<AlumnoResponseDTO>> veralumnos(@PathVariable Long iduser){
        return ResponseEntity.status(HttpStatus.OK).body(alumnoService.buscarPorIdUser(iduser));
    }

}

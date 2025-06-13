package microservice.auth.controller;

import microservice.auth.dto.AuthRequestDTO;
import microservice.auth.dto.AuthResponseDTO;
import microservice.auth.service.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthServiceImpl authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO authRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(authRequestDTO));
    }

}

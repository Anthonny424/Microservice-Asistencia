package com.microservice.alumnos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profe")
public class ProfesorController {

    @GetMapping("/testprofe")
    public String dsdsfdsf(){
        return "Este endpoint solo accede el usuario con rol profesor";
    }

    //Ver perfil y actualizarlo pendiente
    //Asistencia por curso
}

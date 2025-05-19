package com.microservice.alumnos.model;

import jakarta.persistence.*;
import lombok.Data;

@Data

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iduser;
    @Column(unique = true)
    private String correo;
    private String contraseña;


}

package com.microservice.alumnos.model;

import jakarta.persistence.*;
import lombok.Data;

@Data

@Entity
@Table(name = "alumnos")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idalumno;
    private String nombre;
    private String apellido;
    @Column(unique = true)
    private int dni;
    private String qr;
    private String foto;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idpadre")
    private Padre padre;


}

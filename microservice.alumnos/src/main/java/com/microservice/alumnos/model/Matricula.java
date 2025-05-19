package com.microservice.alumnos.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data

@Entity
@Table(name = "matriculas")
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idmatricula;
    private Date fechamatricula;
    private int añoelectivo;
    private String estado;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idalumno")
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "idgrado")
    private Grado grado;


}

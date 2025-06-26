package com.microservice.alumnos.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data


@Entity
@Table(name = "asistenciascursos")
public class AsistenciaCurso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idasistenciacurso;
    private String estado;

    @Column(name = "fecha_asistencia")
    private Date fechaAsistencia;

    @ManyToOne
    @JoinColumn(name = "idalumno")
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "idcurso")
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "idprofesor")
    private Profesor profesor;


}

package com.microservice.alumnos.model;

import jakarta.persistence.*;
import lombok.Data;


@Data

@Entity
@Table(name = "cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcurso;
    private String nombrecurso;

    @ManyToOne
    @JoinColumn(name = "idgrado")
    private Grado grado;

    @ManyToOne
    @JoinColumn(name = "idprofesor")
    private Profesor profesor;

}

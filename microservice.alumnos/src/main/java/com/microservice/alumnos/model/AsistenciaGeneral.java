package com.microservice.alumnos.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data

@Entity
@Table(name = "asistenciasgenerales")
public class AsistenciaGeneral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idasistencia;
    private Date fecharegistrada;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "idalumno")
    private Alumno alumno;


}

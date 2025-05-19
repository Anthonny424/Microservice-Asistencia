package com.microservice.alumnos.model;

import jakarta.persistence.*;
import lombok.Data;

@Data

@Entity
@Table(name = "profesores")
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idprofesor;
    private String nombre;
    private String apellido;
    @Column(unique = true)
    private int dni;
    private int telefono;
    private String especialidad;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "iduser")
    private Usuario usuario;


}

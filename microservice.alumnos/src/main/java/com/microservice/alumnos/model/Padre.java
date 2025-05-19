package com.microservice.alumnos.model;

import jakarta.persistence.*;
import lombok.Data;

@Data

@Entity
@Table(name = "padres")
public class Padre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idpadre;
    private String nombre;
    private String apellido;
    @Column(unique = true)
    private int dni;
    private String direccion;
    private Long telefono;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "iduser")
    private Usuario usuario;


}

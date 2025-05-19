package com.microservice.alumnos.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "grados")
public class Grado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idgrado;
    private String grado;
}

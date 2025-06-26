package com.microservice.alumnos.repository;

import com.microservice.alumnos.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
    Profesor findByUsuarioIduser(Long iduser);
    Profesor findByDni(int dni);
}

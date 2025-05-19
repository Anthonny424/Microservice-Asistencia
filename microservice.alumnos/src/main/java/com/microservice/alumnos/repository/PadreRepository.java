package com.microservice.alumnos.repository;

import com.microservice.alumnos.model.Padre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PadreRepository extends JpaRepository<Padre, Long> {
    Padre findByUsuarioIduser(Long iduser);
    Padre findByDni(int dni);
}

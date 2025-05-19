package com.microservice.alumnos.repository;

import com.microservice.alumnos.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    Alumno findByDni(int dni);

    @Query(value = "SELECT * FROM alumnos a WHERE a.idalumno NOT IN (SELECT s.idalumno FROM asistenciasgenerales s WHERE DATE(s.fecharegistrada) = CURRENT_DATE)", nativeQuery = true)
    List<Alumno>findAlumnosQueNoAsistieronHoy();

    List<Alumno> findAllByPadreUsuarioIduser(Long iduser);
}

package com.microservice.alumnos.repository;

import com.microservice.alumnos.model.AsistenciaCurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsistenciaCursoRepository extends JpaRepository<AsistenciaCurso, Long> {

    @Query(value = "CALL resumen_asistencia_por_curso(:id_alumno, :id_curso)", nativeQuery = true)
    List<Object[]> obtenerResumenAsistencia(@Param("id_alumno") Long idalumno, @Param("id_curso") Long idcurso);
}

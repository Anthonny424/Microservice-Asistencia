package com.microservice.alumnos.repository;


import com.microservice.alumnos.model.AsistenciaGeneral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsistenciaGeneralRepository extends JpaRepository<AsistenciaGeneral, Long> {

    @Query(value = "CALL obtener_asistencias_semana(:idalumno)", nativeQuery = true)
    List<Object[]> obtenerResumenSemanalAsistencia(@Param("idalumno") Long idalumno);

    @Query(value = "CALL obtener_asistencias_recientes(:idalumno)", nativeQuery = true)
    List<Object[]> resumenUltimos7Dias(@Param("idalumno") Long idalumno);

    @Query("SELECT COUNT(a) FROM AsistenciaGeneral a " +
            "WHERE a.alumno.idalumno = :idalumno " +
            "AND DATE(a.fecharegistrada) = CURRENT_DATE")
    int contarAsistenciasHoy(@Param("idalumno") Long idalumno);


}

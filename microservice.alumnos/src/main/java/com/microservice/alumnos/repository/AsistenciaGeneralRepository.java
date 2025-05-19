package com.microservice.alumnos.repository;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.microservice.alumnos.model.AsistenciaGeneral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsistenciaGeneralRepository extends JpaRepository<AsistenciaGeneral, Long> {


}

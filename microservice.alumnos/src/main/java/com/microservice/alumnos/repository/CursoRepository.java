package com.microservice.alumnos.repository;

import com.microservice.alumnos.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByProfesorUsuarioIduser(Long iduser);

    @Query(
            value = "SELECT c.idcurso, c.nombrecurso, g.grado, p.nombre, p.apellido AS profesor_correo " +
                    "FROM matriculas m " +
                    "JOIN grados g ON m.idgrado = g.idgrado " +
                    "JOIN cursos c ON c.idgrado = g.idgrado " +
                    "JOIN profesores p ON c.idprofesor = p.idprofesor " +
                    "JOIN usuarios u ON p.iduser = u.iduser " +
                    "WHERE m.idalumno = :idalumno",
            nativeQuery = true
    )
    List<Object[]> obtenerCursosPorAlumno(@Param("idalumno") Long idalumno);
}

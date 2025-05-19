package com.microservice.alumnos.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AsistenciaGeneralDTO {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Lima")
    private Date fecharegistrada;
    private String estado;
    private Long idalumno;

    public AsistenciaGeneralDTO() {
    }

    public AsistenciaGeneralDTO(Date fecharegistrada, String estado, Long idalumno) {
        this.fecharegistrada = fecharegistrada;
        this.estado = estado;
        this.idalumno = idalumno;
    }

    public Date getFecharegistrada() {
        return fecharegistrada;
    }

    public void setFecharegistrada(Date fecharegistrada) {
        this.fecharegistrada = fecharegistrada;
    }

    public Long getIdalumno() {
        return idalumno;
    }

    public void setIdalumno(Long idalumno) {
        this.idalumno = idalumno;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

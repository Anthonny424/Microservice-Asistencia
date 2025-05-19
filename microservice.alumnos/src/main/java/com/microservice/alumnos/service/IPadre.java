package com.microservice.alumnos.service;

import com.microservice.alumnos.dto.request.PadreCreateDTO;
import com.microservice.alumnos.dto.response.PadreResponseDTO;
import com.microservice.alumnos.model.Padre;

public interface IPadre {
    PadreCreateDTO buscarPorId(Long id);
    void actualizarPadre(PadreCreateDTO padreCreateDTO);

}

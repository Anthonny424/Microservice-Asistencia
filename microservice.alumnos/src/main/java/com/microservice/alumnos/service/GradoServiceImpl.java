package com.microservice.alumnos.service;

import com.microservice.alumnos.dto.response.GradoResponseDTO;
import com.microservice.alumnos.model.Grado;
import com.microservice.alumnos.repository.GradoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GradoServiceImpl implements IGrado{

    @Autowired
    private GradoRepository gradoRepository;

    @Override
    public List<GradoResponseDTO> listarGrados() {
        List<Grado> grados = gradoRepository.findAll();
        List<GradoResponseDTO> gradoResponseDTOS = new ArrayList<>();
        for (Grado x: grados){
            GradoResponseDTO dto = new GradoResponseDTO();
            dto.setIdgrado(x.getIdgrado());
            dto.setGrado(x.getGrado());
            gradoResponseDTOS.add(dto);
        }
        return gradoResponseDTOS;
    }

    @Override
    public Grado findById(Long id) {
        return gradoRepository.findById(id).get();
    }
}

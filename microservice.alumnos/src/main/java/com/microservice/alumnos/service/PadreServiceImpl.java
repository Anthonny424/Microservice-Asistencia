package com.microservice.alumnos.service;

import com.microservice.alumnos.dto.request.PadreCreateDTO;
import com.microservice.alumnos.dto.request.UserCreateDTO;
import com.microservice.alumnos.model.Padre;
import com.microservice.alumnos.model.Usuario;
import com.microservice.alumnos.repository.PadreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PadreServiceImpl implements IPadre{

    @Autowired
    private PadreRepository padreRepository;

    @Override
    public PadreCreateDTO buscarPorId(Long id) {
        Padre padre = padreRepository.findByUsuarioIduser(id);
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setCorreo(padre.getUsuario().getCorreo());
        userCreateDTO.setContraseña("");
        PadreCreateDTO padreCreateDTO = new PadreCreateDTO();
        padreCreateDTO.setNombre(padre.getNombre());
        padreCreateDTO.setApellido(padre.getApellido());
        padreCreateDTO.setDni(padre.getDni());
        padreCreateDTO.setTelefono(padre.getTelefono());
        padreCreateDTO.setDireccion(padre.getDireccion());
        padreCreateDTO.setUserCreateDTO(userCreateDTO);
        return padreCreateDTO;
    }

    @Override
    public void actualizarPadre(PadreCreateDTO padreCreateDTO) {
        Padre padreActualizado = padreRepository.findByDni(padreCreateDTO.getDni());
        padreActualizado.setDireccion(padreCreateDTO.getDireccion());
        padreActualizado.setTelefono(padreCreateDTO.getTelefono());

        Usuario usuario = new Usuario();
        usuario.setIduser(padreActualizado.getUsuario().getIduser());
        usuario.setCorreo(padreCreateDTO.getUserCreateDTO().getCorreo());
        usuario.setContraseña(padreCreateDTO.getUserCreateDTO().getContraseña());

        padreActualizado.setUsuario(usuario);
        padreRepository.save(padreActualizado);
    }

}

package microservice.auth.service;

import microservice.auth.entity.Usuario;
import microservice.auth.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuario{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Optional<Usuario> findByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }
}

package microservice.auth.service;

import microservice.auth.entity.Usuario;

import java.util.Optional;

public interface IUsuario {
    Optional<Usuario> findByCorreo(String correo);
}

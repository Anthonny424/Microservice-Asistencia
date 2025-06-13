package microservice.auth.service;

import microservice.auth.dto.AuthRequestDTO;
import microservice.auth.dto.AuthResponseDTO;
import microservice.auth.entity.Usuario;
import microservice.auth.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    public AuthResponseDTO login(AuthRequestDTO authRequestDTO){
        Usuario usuario = usuarioService.findByCorreo(authRequestDTO.getCorreo()).get();

        if (!passwordEncoder.matches(authRequestDTO.getContraseña(), usuario.getContraseña())){
            throw new BadCredentialsException("Contraseña incorrecta");
        }
        String token = jwtUtils.createToken(usuario.getCorreo(), usuario.getRol().name());
        return new AuthResponseDTO(token, usuario.getIduser());
    }
}

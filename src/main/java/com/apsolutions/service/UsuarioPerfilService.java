package com.apsolutions.service;

import com.apsolutions.dto.UsuarioPerfilDto;
import com.apsolutions.exception.CsException;
import com.apsolutions.model.Usuario;
import com.apsolutions.repository.UsuarioPerfilRepository;
import com.apsolutions.repository.UsuarioRepository;
import com.apsolutions.util.ApiResponse;
import com.apsolutions.util.Global;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioPerfilService {

    private final UsuarioPerfilRepository usuarioPerfilRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    public UsuarioPerfilService(UsuarioPerfilRepository usuarioPerfilRepository) {
        this.usuarioPerfilRepository = usuarioPerfilRepository;
    }

    @Transactional
    public ApiResponse<String> updateUsernamePassword(UsuarioPerfilDto usuario, HttpServletRequest request){
        Usuario userLogin = tokenService.getUserByToken(request.getHeader(HttpHeaders.AUTHORIZATION));

        Optional<Usuario> optionalUsuario = usuarioRepository.searchById(userLogin.getId());

        if (optionalUsuario.isPresent()){
            if (!usuario.getUsuario().isEmpty()){
                usuarioPerfilRepository.updateUsername(usuario.getUsuario(), userLogin.getId());
            }

            if (!usuario.getClaveNueva().isEmpty()){
                usuario.setClaveNueva(passwordEncoder.encode(usuario.getClaveNueva()));
                /*usuario.setClaveAnterior(passwordEncoder.encode(usuario.getClaveAnterior()));
                if (!userLogin.getClave().equals(usuario.getClaveAnterior())){
                    //throw new CsException("La clave anterior del usuario es incorrecta.");
                }*/
                usuarioPerfilRepository.updatePassword(usuario.getClaveNueva(), userLogin.getId());
            }

            return new ApiResponse<>(true, Global.SUCCESSFUL_UPDATE_MESSAGE);
        }else{
            throw new CsException(Global.REGISTER_NOT_FOUND);
        }

    }
}

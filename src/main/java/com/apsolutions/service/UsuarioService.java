package com.apsolutions.service;

import com.apsolutions.dto.UsuarioDto;
import com.apsolutions.exception.CsException;
import com.apsolutions.model.Usuario;
import com.apsolutions.repository.UsuarioRepository;
import com.apsolutions.util.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public ApiResponse<List<UsuarioDto>> list() {
        return new ApiResponse<>(true, "OK", usuarioRepository.list());
    }

    private void checkValidations(String use, int id) {
        Optional<Usuario> optionalUsuario = usuarioRepository.existsByUsername(use, id);
        if (optionalUsuario.isPresent()) {
            throw new CsException("El usuario " + use + " ya se encuentra registrado.");
        }
    }

    public ApiResponse<String> save(Usuario usuario) {
        checkValidations(usuario.getUsuario(), 0);
        usuario.setClave(passwordEncoder.encode(usuario.getClave()));
        usuario.setEstado(true);
        usuario.setBloqueado(Byte.parseByte("0"));
        usuarioRepository.save(usuario);
        return new ApiResponse<>(true, "Se registro correctamente.");
    }

    public ApiResponse<String> edit(Integer id, Usuario usuario) {
        usuario.setId(id);
        checkValidations(usuario.getUsuario(), usuario.getId());
        usuarioRepository.save(usuario);
        return new ApiResponse<>(true, "Se modificó correctamente.");
    }

    public ApiResponse<String> delete(Integer id, Integer perfil_id, String username) {
        if (!usuarioRepository.existsById(id)) {
            throw new CsException("No se encontró registro.");
        }

        Optional<Usuario> optionalUsuario = usuarioRepository.existsByUsername(username, 0);
        if (optionalUsuario.get().getId() == id) {
            throw new CsException("No es posible eliminar el usuario con el que esta logeado.");
        } else if (perfil_id == 1) {
            throw new CsException("No es posible eliminar al usuario por ser administrador.");
        }

        usuarioRepository.updateStatus(false, id);

        return new ApiResponse<>(true, "Se eliminó correctamente.");
    }

    public ApiResponse<String> bloquear(Integer id, HttpServletRequest request) {
        if (!usuarioRepository.existsById(id)) {
            throw new CsException("No se encontró registro.");
        }

        Usuario usuario = tokenService.getUserByToken(request.getHeader(HttpHeaders.AUTHORIZATION));
        if (usuario.getId() == id) {
            throw new CsException("No es posible bloquear el usuario con el que esta logeado.");
        }

        Byte bloqueo = 0;
        Optional<Usuario> optionalUsuario = usuarioRepository.searchById(id);
        if (optionalUsuario.isPresent()){
            if (optionalUsuario.get().getBloqueado()==1){
                bloqueo = 0;
            }else{
                bloqueo = 1;
            }
        }
        usuarioRepository.updateBloqueo(id, bloqueo);
        return new ApiResponse<>(true, "Se " + (bloqueo == 1 ? "bloqueó" : "desbloqueo") + " correctamente.");
    }

    public ApiResponse<UsuarioDto> getAuthenticatedUser(HttpServletRequest request) {
        Usuario usuario = tokenService.getUserByToken(request.getHeader(HttpHeaders.AUTHORIZATION));
        UsuarioDto usuarioDto = new UsuarioDto(usuario.getId(), usuario.getNombre(), usuario.getUsuario(), usuario.getBloqueado(), usuario.getPerfil().getNombre(), usuario.getPerfil().getId());
        return new ApiResponse<>(true, "Ok", usuarioDto);
    }
}

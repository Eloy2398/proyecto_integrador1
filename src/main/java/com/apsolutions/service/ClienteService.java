package com.apsolutions.service;

import com.apsolutions.dto.ClienteDto;
import com.apsolutions.exception.CsException;
import com.apsolutions.model.Cliente;
import com.apsolutions.model.Persona;
import com.apsolutions.repository.ClienteRepository;
import com.apsolutions.repository.PersonaRepository;
import com.apsolutions.util.ApiResponse;
import com.apsolutions.util.Global;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final PersonaRepository personaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    private void checkValidation(String documento, Integer id) {
        Optional<Persona> optionalPersona = personaRepository.existsByDocument(documento, id);
        if (optionalPersona.isPresent()) {
            throw new CsException("El cliente con número de documento " + documento + " existe.");
        }
    }

    @Transactional
    public ApiResponse<String> save(Persona persona) {
        if (persona.getId() == null) {
            persona.setId(0);
        }

        if (persona.getId() > 0) {
            checkValidation(persona.getDocumento(), persona.getId());
            personaRepository.save(persona);
            return new ApiResponse<>(true, Global.SUCCESSFUL_UPDATE_MESSAGE);
        } else {
            Optional<Cliente> optionalCliente = clienteRepository.existsByDocument(persona.getDocumento(), 0);
            if (optionalCliente.isPresent()) {
                throw new CsException("El cliente con número de documento " + persona.getDocumento() + " existe.");
            }

            Optional<Persona> optionalPersona = personaRepository.existsByDocument(persona.getDocumento(), 0);
            if (optionalPersona.isPresent()) {
                persona = optionalPersona.get();
            } else {
                persona = personaRepository.save(persona);
            }

            Cliente cliente = new Cliente();
            cliente.setPersona(persona);
            cliente.setEstado(true);

            clienteRepository.save(cliente);
            return new ApiResponse<>(true, Global.SUCCESSFUL_INSERT_MESSAGE);
        }
    }

    public ApiResponse<Persona> read(Integer id) {
        return new ApiResponse<>(true, "OK", personaRepository.findById(id).orElse(null));
    }

    public ApiResponse<List<ClienteDto>> list() {
        return new ApiResponse<>(true, "OK", clienteRepository.list());
    }

    @Transactional
    public ApiResponse<String> delete(Integer id) {
        Optional<Cliente> optionalCliente = clienteRepository.obtenerById(id);
        if (optionalCliente.isPresent()) {
            clienteRepository.updateStatus(false, optionalCliente.get().getId());
            return new ApiResponse<>(true, Global.SUCCESSFUL_DELETE_MESSAGE);
        } else {
            throw new CsException(Global.REGISTER_NOT_FOUND);
        }
    }
}

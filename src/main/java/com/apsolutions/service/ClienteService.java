package com.apsolutions.service;

import com.apsolutions.exception.CsException;
import com.apsolutions.model.Cliente;
import com.apsolutions.model.Persona;
import com.apsolutions.repository.ClienteRepository;
import com.apsolutions.repository.PersonaRepository;
import com.apsolutions.util.ApiResponse;
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

    private void checkValidation(String documento, Integer id){
        Optional<Persona> optionalPersona = personaRepository.existsByDocument(documento, id);

        if (optionalPersona.isPresent()){
            throw new CsException("El número de documento "+documento+" existe.");
        }
    }

    @Transactional
    public ApiResponse<String> save(Persona persona){
        checkValidation(persona.getDocumento(), 0);
        persona = personaRepository.save(persona);

        Cliente cliente = new Cliente();
        cliente.setPersona(persona);
        cliente.setEstado(true);
        clienteRepository.save(cliente);
        return new ApiResponse<>(true, "Se registro correctamente");
    }

    @Transactional
    public ApiResponse<String> edit(Integer id, Persona persona){
        persona.setId(id);
        checkValidation(persona.getDocumento(), persona.getId());
        personaRepository.save(persona);
        return new ApiResponse<>(true, "Se modificó correctamente");
    }

    public ApiResponse<List<Cliente>> list(){
        return new ApiResponse<>(true, "OK", clienteRepository.list());
    }
}

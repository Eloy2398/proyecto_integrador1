package com.apsolutions.controller;

import com.apsolutions.dto.ClienteDto;
import com.apsolutions.dto.PersonaDto;
import com.apsolutions.model.Categoria;
import com.apsolutions.model.Cliente;
import com.apsolutions.model.Persona;
import com.apsolutions.service.ClienteService;
import com.apsolutions.util.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cliente")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/guardar")
    public ApiResponse<String> save(@RequestBody Persona persona) {
        return clienteService.save(persona);
    }

    @GetMapping("/leer/{id}")
    public ApiResponse<Persona> read(@PathVariable("id") Integer id) {
        return clienteService.read(id);
    }

    @GetMapping("/listar")
    public ApiResponse<List<ClienteDto>> list() {
        return clienteService.list();
    }

    @PutMapping("/eliminar/{id}")
    public ApiResponse<String> delete(@PathVariable("id") Integer id) {
        return clienteService.delete(id);
    }
}

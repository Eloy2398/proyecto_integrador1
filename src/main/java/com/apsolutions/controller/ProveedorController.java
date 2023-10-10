package com.apsolutions.controller;

import com.apsolutions.dto.ProveedorDto;
import com.apsolutions.model.Persona;
import com.apsolutions.service.ProveedorService;
import com.apsolutions.util.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/proveedor")
public class ProveedorController {

    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @PostMapping("/guardar")
    public ApiResponse<String> save(@RequestBody Persona persona){
        return proveedorService.save(persona);
    }

    @PutMapping("/editar/{id}")
    public ApiResponse<String> edit(@PathVariable("id") Integer id, @RequestBody Persona persona){
        return proveedorService.edit(id, persona);
    }

    @GetMapping("/listar")
    public ApiResponse<List<ProveedorDto>> list(){
        return proveedorService.list();
    }

    @PutMapping("/eliminar/{id}")
    public ApiResponse<String> delete(@PathVariable("id") Integer id){
        return proveedorService.delete(id);
    }
}

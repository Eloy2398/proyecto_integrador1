package com.apsolutions.service;

import com.apsolutions.model.Marca;
import com.apsolutions.repository.MarcaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MarcaService {
    private final MarcaRepository marcaRepository;

    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    public List<Marca> list() {
        return this.marcaRepository.findAll();
    }

    public Marca save(Marca marca) {
        return this.marcaRepository.save(marca);
    }

    public Marca edit(Integer id, Marca marca) {
        marca.setId(id);
        return this.marcaRepository.save(marca);
    }
}

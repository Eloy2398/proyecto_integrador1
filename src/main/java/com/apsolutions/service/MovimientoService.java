package com.apsolutions.service;

import com.apsolutions.exception.CsException;
import com.apsolutions.model.Movimiento;
import com.apsolutions.repository.MovimientoRepository;
import com.apsolutions.util.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

@Service
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;

    public MovimientoService(MovimientoRepository movimientoRepository) {
        this.movimientoRepository = movimientoRepository;
    }

    public ApiResponse<String> save(Movimiento movimiento) {
        try {
            movimientoRepository.save(movimiento);
            return new ApiResponse<>(true, "Se registró correctamente");
        } catch (DataIntegrityViolationException | JpaObjectRetrievalFailureException e) {
            throw new CsException("Error de integridad de datos " + e.getMessage());
        }
    }
}

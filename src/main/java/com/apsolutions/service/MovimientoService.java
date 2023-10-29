package com.apsolutions.service;

import com.apsolutions.dto.MovimientoDto;
import com.apsolutions.dto.MovimientoListDto;
import com.apsolutions.dto.PersonaBusquedaDto;
import com.apsolutions.dto.ProductoBusquedaDto;
import com.apsolutions.exception.CsException;
import com.apsolutions.mapper.MovimientoMapper;
import com.apsolutions.model.Movimiento;
import com.apsolutions.repository.MovimientoRepository;
import com.apsolutions.repository.MovimientodetalleRepository;
import com.apsolutions.repository.PersonaRepository;
import com.apsolutions.repository.ProductoRepository;
import com.apsolutions.util.ApiResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;

    @Autowired
    private MovimientodetalleRepository movimientodetalleRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private MovimientoMapper movimientoMapper;

    @Autowired
    private PersonaRepository personaRepository;

    public MovimientoService(MovimientoRepository movimientoRepository) {
        this.movimientoRepository = movimientoRepository;
    }

    @Transactional
    public ApiResponse<String> save(MovimientoDto movimientoDto) {
        try {
            Movimiento movimiento = movimientoMapper.toEntity(movimientoDto);
            movimiento.setEstado(true);
            movimiento.setFecharegistro(new Date());

            Movimiento movimientoSaved = movimientoRepository.save(movimiento);

            movimientoDto.getMovimientodetalleList().forEach(movimientodetalle -> {
                movimientodetalle.setMovimiento(movimientoSaved);
                movimientodetalleRepository.save(movimientodetalle);

                int stock = productoRepository.getStock(movimientodetalle.getProducto().getId());
                if (movimientoSaved.getTipo() == 1) {
                    stock += movimientodetalle.getCantidad();
                } else {
                    stock -= movimientodetalle.getCantidad();
                }
                productoRepository.updateStock(stock, movimientodetalle.getProducto().getId());
            });

            return new ApiResponse<>(true, "Se registró correctamente");
        } catch (DataIntegrityViolationException | JpaObjectRetrievalFailureException e) {
            throw new CsException("Error de integridad de datos " + e.getMessage());
        }
    }

    public ApiResponse<List<MovimientoListDto>> list() {
        return new ApiResponse<>(true, "Ok", movimientoRepository.list());
    }

    @Transactional
    public ApiResponse<String> unregister(Integer id) {
        Optional<Movimiento> movimiento = movimientoRepository.findById(id);
        if (movimiento.isPresent()) {
            if (!movimiento.get().getEstado()) {
                throw new CsException("Movimiento ya anulado");
            }

            int type = movimiento.get().getTipo();

            movimientodetalleRepository.listByIdMovimiento(id).forEach(movimientodetalle -> {
                int stock = productoRepository.getStock(movimientodetalle.getProducto().getId());
                if (type == 1) {
                    stock -= movimientodetalle.getCantidad();
                } else {
                    stock += movimientodetalle.getCantidad();
                }
                productoRepository.updateStock(stock, movimientodetalle.getProducto().getId());
            });

            movimientoRepository.updateStatus(false, id);

            return new ApiResponse<>(true, "Se anuló correctamente");
        } else {
            throw new CsException("Movimiento no encontrado");
        }
    }

    public ApiResponse<List<PersonaBusquedaDto>> searchPerson(String query) {
        return new ApiResponse<>(true, "Ok", personaRepository.search(query + "%"));
    }

    public ApiResponse<List<ProductoBusquedaDto>> searchProduct(String query) {
        return new ApiResponse<>(true, "Ok", productoRepository.search(query + "%"));
    }
}

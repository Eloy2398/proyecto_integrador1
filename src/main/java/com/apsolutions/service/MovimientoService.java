package com.apsolutions.service;

import com.apsolutions.dto.*;
import com.apsolutions.dto.query.PersonaDto;
import com.apsolutions.dto.report.MovimientoReportDto;
import com.apsolutions.exception.CsException;
import com.apsolutions.mapper.MovimientoMapper;
import com.apsolutions.model.Movimiento;
import com.apsolutions.model.Movimientodetalle;
import com.apsolutions.repository.*;
import com.apsolutions.util.ApiResponse;
import com.apsolutions.util.Global;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import java.util.*;

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
    @Autowired
    private CotizacionRepository cotizacionRepository;

    public MovimientoService(MovimientoRepository movimientoRepository) {
        this.movimientoRepository = movimientoRepository;
    }

    @Transactional
    public ApiResponse<String> save(MovimientoDto movimientoDto) {
        try {
            if (movimientoDto.getTipo() == Global.OUTPUT_TYPE) {
                checkStockProducts(movimientoDto.getMovimientodetalleList());
            }

            Movimiento movimiento = movimientoMapper.toEntity(movimientoDto);
            movimiento.setEstado(true);
            movimiento.setFecharegistro(new Date());

            if (movimientoDto.getIdPersona() != null && movimientoDto.getIdPersona() > 0) {
                movimiento.setPersona(personaRepository.findById(movimientoDto.getIdPersona()).orElse(null));
            }

            if (movimientoDto.getIdCotizacion() != null && movimientoDto.getIdCotizacion() > 0) {
                movimiento.setCotizacion(cotizacionRepository.findById(movimientoDto.getIdCotizacion()).orElse(null));
            }

            Movimiento movimientoSaved = movimientoRepository.save(movimiento);

            movimientoDto.getMovimientodetalleList().forEach(movimientodetalleDto -> {
                Movimientodetalle movimientodetalle = new Movimientodetalle();
                movimientodetalle.setMovimiento(movimientoSaved);
                movimientodetalle.setProducto(productoRepository.findById(movimientodetalleDto.getIdProducto()).orElse(null));
                movimientodetalle.setPrecio(movimientodetalleDto.getPrecio());
                movimientodetalle.setCantidad(movimientodetalleDto.getCantidad());
                movimientodetalleRepository.save(movimientodetalle);

                int stock = productoRepository.getStock(movimientodetalleDto.getIdProducto());
                if (movimientoSaved.getTipo() == Global.INPUT_TYPE) {
                    stock += movimientodetalleDto.getCantidad();
                } else {
                    stock -= movimientodetalleDto.getCantidad();
                }
                productoRepository.updateStock(stock, movimientodetalleDto.getIdProducto());
            });

            return new ApiResponse<>(true, Global.SUCCESSFUL_INSERT_MESSAGE);
        } catch (DataIntegrityViolationException | JpaObjectRetrievalFailureException e) {
            throw new CsException(Global.DATA_INTEGRITY_ERROR + e.getMessage());
        }
    }

    private void checkStockProducts(List<MovimientodetalleDto> movimientodetalleList) {
        List<String> strProductList = new ArrayList<>();

        movimientodetalleList.forEach(movimientodetalle -> {
            if (productoRepository.getStock(movimientodetalle.getIdProducto()) < movimientodetalle.getCantidad()) {
                strProductList.add(movimientodetalle.getNombreProducto());
            }
        });

        if (!strProductList.isEmpty()) {
            throw new CsException("Los productos " + strProductList + " no cuentan con stock suficiente");
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
                if (type == Global.INPUT_TYPE) {
                    stock -= movimientodetalle.getCantidad();
                } else {
                    stock += movimientodetalle.getCantidad();
                }
                productoRepository.updateStock(stock, movimientodetalle.getProducto().getId());
            });

            movimientoRepository.updateStatus(false, id);

            return new ApiResponse<>(true, Global.SUCCESSFUL_DEREGISTER_MESSAGE);
        } else {
            throw new CsException("Movimiento no encontrado");
        }
    }

    public ApiResponse<List<PersonaDto>> searchPerson(String query) {
        return new ApiResponse<>(true, "Ok", personaRepository.search(query + "%"));
    }

    public ApiResponse<MovimientoDto> read(Integer id) {
        MovimientoDto movimientoDto = movimientoRepository.read(id);
        movimientoDto.setMovimientodetalleList(movimientodetalleRepository.listByIdMovimientoSimplifado(id));

        return new ApiResponse<>(true, "Ok", movimientoDto);
    }

    public ApiResponse<List<MovimientoReportDto>> filter(MovimientoReportDto movimientoReportDto) {
        if (movimientoReportDto.getIdProducto() == null || movimientoReportDto.getIdProducto() <= 0){
            throw new CsException("Seleccione un producto en el filtro.");
        }

        Date fec1 = movimientoReportDto.getFecha1();
        Date fec2 = movimientoReportDto.getFecha2();
        Integer idProducto = movimientoReportDto.getIdProducto();

        return new ApiResponse<>(true, "OK", movimientoRepository.filter(fec1,fec2,idProducto));

    }
}

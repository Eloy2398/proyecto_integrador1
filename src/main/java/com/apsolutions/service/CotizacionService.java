package com.apsolutions.service;

import com.apsolutions.dto.ClienteBusquedaDto;
import com.apsolutions.dto.CotizacionDto;
import com.apsolutions.dto.CotizacionListDto;
import com.apsolutions.exception.CsException;
import com.apsolutions.mapper.CotizacionMapper;
import com.apsolutions.model.Cotizacion;
import com.apsolutions.repository.ClienteRepository;
import com.apsolutions.repository.CotizacionRepository;
import com.apsolutions.repository.CotizaciondetalleRepository;
import com.apsolutions.util.ApiResponse;
import com.apsolutions.util.Global;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CotizacionService {
    private final CotizacionRepository cotizacionRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CotizacionMapper cotizacionMapper;

    @Autowired
    private CotizaciondetalleRepository cotizaciondetalleRepository;

    public CotizacionService(CotizacionRepository cotizacionRepository) {
        this.cotizacionRepository = cotizacionRepository;
    }

    @Transactional
    public ApiResponse<String> save(CotizacionDto cotizacionDto){
        try {
            Cotizacion cotizacion = cotizacionMapper.toEntity(cotizacionDto);
            cotizacion.setEstado(true);
            cotizacion.setFecha(new Date());

            Cotizacion cotizacionSaved = cotizacionRepository.save(cotizacion);

            cotizacionDto.getCotizaciondetalleList().forEach(cotizaciondetalle -> {
                cotizaciondetalle.setCotizacion(cotizacionSaved);
                cotizaciondetalleRepository.save(cotizaciondetalle);
            });

            return new ApiResponse<>(true, Global.SUCCESSFUL_INSERT_MESSAGE);
        } catch (DataIntegrityViolationException | JpaObjectRetrievalFailureException e){
            throw new CsException(Global.DATA_INTEGRITY_ERROR + e.getMessage());
        }
    }

    @Transactional
    public ApiResponse<String> anular(Integer id) {
        if (!cotizacionRepository.existsById(id)) {
            throw new CsException(Global.REGISTER_NOT_FOUND);
        }

        cotizacionRepository.updateStatus(false, id);

        return new ApiResponse<>(true, Global.SUCCESSFUL_DELETE_MESSAGE);
    }

    public ApiResponse<CotizacionDto> read(Integer id){
        CotizacionDto cotizacionDto = cotizacionMapper.toDto(cotizacionRepository.findById(id).orElse(null));
        cotizacionDto.setCotizaciondetalleList(cotizaciondetalleRepository.listByIdCotizacion(id));

        return new ApiResponse<>(true, "OK", cotizacionDto);
    }

    public ApiResponse<List<CotizacionListDto>> list() {
        return new ApiResponse<>(true, "OK", cotizacionRepository.list());
    }

    public ApiResponse<List<ClienteBusquedaDto>> searchClient(String query){
        return new ApiResponse<>(true, "Ok", clienteRepository.search(query + "%"));
    }
}

package com.apsolutions.service;

import com.apsolutions.dto.CotizacionDto;
import com.apsolutions.dto.CotizacionListDto;
import com.apsolutions.dto.CotizaciondetalleDto;
import com.apsolutions.dto.JasperReportDto;
import com.apsolutions.dto.query.CotizacionQueryDto;
import com.apsolutions.dto.query.PersonaQueryDto;
import com.apsolutions.dto.report.CotizacionReportDto;
import com.apsolutions.exception.CsException;
import com.apsolutions.mapper.CotizacionMapper;
import com.apsolutions.model.Cotizacion;
import com.apsolutions.model.Cotizaciondetalle;
import com.apsolutions.repository.*;
import com.apsolutions.repository.custom.CotizacionReportRepository;
import com.apsolutions.util.ApiResponse;
import com.apsolutions.util.Global;
import com.apsolutions.util.JasperReportGenerator;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.Resource;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CotizacionService {
    private final CotizacionRepository cotizacionRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CotizacionReportRepository cotizacionReportRepository;

    @Autowired
    private CotizacionMapper cotizacionMapper;

    @Autowired
    private CotizaciondetalleRepository cotizaciondetalleRepository;
    @Autowired
    private CotizacionCriterioopcionRepository cotizacionCriterioopcionRepository;

    @Autowired
    private JasperReportGenerator jasperReportGenerator;

    public CotizacionService(CotizacionRepository cotizacionRepository) {
        this.cotizacionRepository = cotizacionRepository;
    }

    @Transactional
    public ApiResponse<Integer> save(CotizacionDto cotizacionDto) {
        try {
            Cotizacion cotizacion = cotizacionMapper.toEntity(cotizacionDto);
            cotizacion.setEstado((byte) 1);
            cotizacion.setFecha(new Date());

            if (cotizacionDto.getIdCliente() != null && cotizacionDto.getIdCliente() > 0) {
                cotizacion.setCliente(clienteRepository.findById(cotizacionDto.getIdCliente()).orElse(null));
            }

            Cotizacion quotationSaved = cotizacionRepository.save(cotizacion);

            if (cotizacionDto.getCotizacionCriterioopcionList() != null) {
                cotizacionDto.getCotizacionCriterioopcionList().forEach(cotizacionCriterioopcion -> {
                    cotizacionCriterioopcion.setCotizacion(quotationSaved);
                    cotizacionCriterioopcionRepository.save(cotizacionCriterioopcion);
                });
            }

            cotizacionDto.getCotizaciondetalleList().forEach(cotizaciondetalleDto -> {
                Cotizaciondetalle cotizaciondetalle = new Cotizaciondetalle();
                cotizaciondetalle.setCotizacion(quotationSaved);
                cotizaciondetalle.setProducto(productoRepository.findById(cotizaciondetalleDto.getIdProducto()).orElse(null));
                cotizaciondetalle.setPrecio(cotizaciondetalleDto.getPrecio());
                cotizaciondetalle.setCantidad(cotizaciondetalleDto.getCantidad());
                cotizaciondetalleRepository.save(cotizaciondetalle);
            });

            return new ApiResponse<>(true, Global.SUCCESSFUL_INSERT_MESSAGE, quotationSaved.getId());
        } catch (DataIntegrityViolationException | JpaObjectRetrievalFailureException e) {
            throw new CsException(Global.DATA_INTEGRITY_ERROR + e.getMessage());
        }
    }

    @Transactional
    public ApiResponse<String> anular(Integer id) {
        if (!cotizacionRepository.existsById(id)) {
            throw new CsException(Global.REGISTER_NOT_FOUND);
        }

        cotizacionRepository.updateStatus((byte) 2, id);

        return new ApiResponse<>(true, Global.SUCCESSFUL_DEREGISTER_MESSAGE);
    }

    public ApiResponse<CotizacionDto> read(Integer id) {
        CotizacionDto cotizacionDto = cotizacionRepository.read(id);
        cotizacionDto.setCotizaciondetalleList(cotizaciondetalleRepository.listByIdCotizacionSimplifado(id));

        return new ApiResponse<>(true, "OK", cotizacionDto);
    }

    public ApiResponse<List<CotizacionListDto>> list() {
        return new ApiResponse<>(true, "OK", cotizacionRepository.list());
    }

    public ApiResponse<List<PersonaQueryDto>> searchClient(String query) {
        return new ApiResponse<>(true, "Ok", clienteRepository.search(query + "%"));
    }

    public ApiResponse<List<CotizacionQueryDto>> search(String query) {
        if (Character.isDigit(query.charAt(0))) {
            return new ApiResponse<>(true, "Ok", cotizacionRepository.searchById(Integer.parseInt(query)));
        } else {
            return new ApiResponse<>(true, "Ok", cotizacionRepository.searchByName(query + "%"));
        }
    }

    public ApiResponse<List<CotizacionReportDto>> filter(String fecha1, String fecha2, int idCliente) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        format.setLenient(true);
        try {
            return new ApiResponse<>(true, "OK", cotizacionReportRepository.filter(format.parse(fecha1), format.parse(fecha2), idCliente));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<Resource> excelReport(String fecha1, String fecha2, int idCliente) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        format.setLenient(true);

        List<CotizacionReportDto> cotizacionReportDtoList;

        try {
            cotizacionReportDtoList = cotizacionReportRepository.filter(format.parse(fecha1), format.parse(fecha2), idCliente);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Map<String, Object> params = new HashMap<>();
        params.put("dateRange", fecha1 + " al " + fecha2);
        params.put("clientName", idCliente > 0 ? clienteRepository.getName(idCliente) : "TODOS");
        params.put("printDate", format.format(new Date()));
        params.put("dts", new JRBeanCollectionDataSource(cotizacionReportDtoList));

        JasperReportDto jasperReportDto = jasperReportGenerator.generateExcel("cotizacionReport", params);

        return ResponseEntity.ok().headers(jasperReportDto.getHeaders()).contentLength(jasperReportDto.getLength()).body(jasperReportDto.getResource());
    }

    public ApiResponse<List<CotizaciondetalleDto>> getDetails(Integer id) {
        return new ApiResponse<>(true, "Ok", cotizaciondetalleRepository.listByIdCotizacionSimplifado(id));
    }
}

package com.apsolutions.service;

import com.apsolutions.dto.CotizacionDto;
import com.apsolutions.repository.CotizacionRepository;
import com.apsolutions.util.ApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CotizacionService {
    private final CotizacionRepository cotizacionRepository;

    public CotizacionService(CotizacionRepository cotizacionRepository) {
        this.cotizacionRepository = cotizacionRepository;
    }

    public ApiResponse<List<CotizacionDto>> list() {
        return new ApiResponse<>(true, "OK", cotizacionRepository.list());
    }
}

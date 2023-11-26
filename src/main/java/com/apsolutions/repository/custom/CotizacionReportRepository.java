package com.apsolutions.repository.custom;

import com.apsolutions.dto.report.CotizacionReportDto;

import java.util.Date;
import java.util.List;

public interface CotizacionReportRepository {
    List<CotizacionReportDto> filter(Date fec1, Date fec2, int idCliente);
}

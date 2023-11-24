package com.apsolutions.repository.custom;

import com.apsolutions.dto.indicator.ProductoDto;

import java.util.List;

public interface ProductoIndicadorRepository {
    List<ProductoDto> getStockListForChart(int anio, int idCategory, int sortByStock);
}

package com.apsolutions.repository.custom;

import com.apsolutions.dto.indicator.ProductoIndicatorDto;

import java.util.List;

public interface ProductoIndicadorRepository {
    List<ProductoIndicatorDto> getStockListForChart(int anio, int idCategory, int sortByStock);
}

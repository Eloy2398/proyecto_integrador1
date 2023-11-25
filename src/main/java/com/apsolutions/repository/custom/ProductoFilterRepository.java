package com.apsolutions.repository.custom;

import com.apsolutions.dto.ProductoListDto;

import java.util.List;

public interface ProductoFilterRepository {
    List<ProductoListDto> filter(int tipo, String nombre, int idCategory, int idBrand);
}

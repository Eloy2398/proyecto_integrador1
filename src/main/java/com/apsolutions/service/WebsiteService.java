package com.apsolutions.service;

import com.apsolutions.dto.website.CategoriaDto;
import com.apsolutions.dto.website.MarcaDto;
import com.apsolutions.dto.website.ProductoDto;
import com.apsolutions.model.Categoria;
import com.apsolutions.repository.CategoriaRepository;
import com.apsolutions.repository.MarcaRepository;
import com.apsolutions.repository.ProductoCaracteristicaRepository;
import com.apsolutions.repository.ProductoRepository;
import com.apsolutions.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebsiteService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private ProductoCaracteristicaRepository productoCaracteristicaRepository;
    @Autowired
    private MarcaRepository marcaRepository;

    public ApiResponse<List<ProductoDto>> getProductsToBanner() {
        PageRequest pageRequest = PageRequest.of(1, 5, Sort.by("id").descending());
        return new ApiResponse<>(true, "Ok", productoRepository.getProductsToBanner(pageRequest));
    }

    public ApiResponse<List<MarcaDto>> getBrandsMain() {
        return new ApiResponse<>(true, "Ok", marcaRepository.getBrandsMain());
    }

    public ApiResponse<List<CategoriaDto>> getCategoriesMain() {
        return new ApiResponse<>(true, "Ok", categoriaRepository.getCategoriesMain());
    }

    public ApiResponse<List<ProductoDto>> getProductsMain() {
        // PageRequest pageRequest = PageRequest.of(1, 8, Sort.by(Sort.Direction.DESC, "id"));
        return new ApiResponse<>(true, "Ok", productoRepository.getProductsMain());
    }

    public ApiResponse<Categoria> validateCategory(Integer id, String urlName) {
        return new ApiResponse<>(true, "Ok", categoriaRepository.validateByIdAndName(id, urlName).orElse(null));
    }

    public ApiResponse<ProductoDto> getProductData(Integer id, String urlName) {
        ProductoDto productoDto = productoRepository.getProductData(id, urlName).orElse(null);

        if (productoDto != null) {
            productoDto.setProductoCaracteristicaList(productoCaracteristicaRepository.findByIdProducto(productoDto.getId()));
        }

        return new ApiResponse<>(true, "Ok", productoDto);
    }
}

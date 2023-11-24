package com.apsolutions.service;

import com.apsolutions.dto.website.*;
import com.apsolutions.model.Categoria;
import com.apsolutions.repository.*;
import com.apsolutions.repository.custom.ProductoWebsiteFilterRepository;
import com.apsolutions.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WebsiteService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProductoWebsiteRepository productoWebsiteRepository;
    @Autowired
    private ProductoWebsiteFilterRepository productoWebsiteFilterRepository;
    @Autowired
    private ProductoCaracteristicaRepository productoCaracteristicaRepository;
    @Autowired
    private MarcaRepository marcaRepository;

    public ApiResponse<List<ProductoWebsiteDto>> getProductsToBanner() {
        PageRequest pageRequest = PageRequest.of(1, 5, Sort.by("id").descending());
        return new ApiResponse<>(true, "Ok", productoWebsiteRepository.getProductsToBanner(pageRequest));
    }

    public ApiResponse<List<MarcaWebsiteDto>> getBrandsMain() {
        return new ApiResponse<>(true, "Ok", marcaRepository.getBrandsMain());
    }

    public ApiResponse<List<MarcaWebsiteDto>> getBrands() {
        return new ApiResponse<>(true, "Ok", marcaRepository.getBrands());
    }

    public ApiResponse<List<MarcaWebsiteDto>> getBrandsCategory(Integer idCategory) {
        return new ApiResponse<>(true, "Ok", marcaRepository.getBrandsCategory(idCategory));
    }

    public ApiResponse<List<CategoriaWebsiteDto>> getCategoriesMain() {
        return new ApiResponse<>(true, "Ok", categoriaRepository.getCategoriesMain());
    }

    public ApiResponse<List<CategoriaWebsiteDto>> getCategories() {
        return new ApiResponse<>(true, "Ok", categoriaRepository.getCategories());
    }

    public ApiResponse<List<ProductoWebsiteDto>> getProductsMain() {
        PageRequest pageRequest = PageRequest.of(1, 12, Sort.by(Sort.Direction.DESC, "id"));
        return new ApiResponse<>(true, "Ok", productoWebsiteRepository.getProductsMain(pageRequest));
    }

    public ApiResponse<Categoria> validateCategory(Integer id, String urlName) {
        return new ApiResponse<>(true, "Ok", categoriaRepository.validateByIdAndName(id, urlName).orElse(null));
    }

    public ApiResponse<ProductoWebsiteDto> getProductData(Integer id, String urlName) {
        ProductoWebsiteDto productoDto = productoWebsiteRepository.getProductData(id, urlName).orElse(null);

        if (productoDto != null) {
            productoDto.setProductoCaracteristicaList(productoCaracteristicaRepository.findByIdProducto(productoDto.getId()));
        }

        return new ApiResponse<>(true, "Ok", productoDto);
    }

    public ApiResponse<Map<String, Object>> getProductsCategory(Integer idCategory, String brand, String strPriceRange, Integer sortBy) {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("records", productoWebsiteFilterRepository.getAll(idCategory, brand, strPriceRange, sortBy));
        objectMap.put("totalRecords", productoWebsiteFilterRepository.getTotalRecords(idCategory, brand, strPriceRange));
        return new ApiResponse<>(true, "Ok", objectMap);
    }
}

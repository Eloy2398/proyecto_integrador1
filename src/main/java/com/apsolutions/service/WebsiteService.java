package com.apsolutions.service;

import com.apsolutions.dto.CotizacionDto;
import com.apsolutions.dto.CotizaciondetalleDto;
import com.apsolutions.dto.website.*;
import com.apsolutions.model.Categoria;
import com.apsolutions.model.CotizacionCriterioopcion;
import com.apsolutions.repository.*;
import com.apsolutions.repository.custom.ProductoWebsiteFilterRepository;
import com.apsolutions.util.ApiResponse;
import com.apsolutions.util.HTMLTemplate;
import com.apsolutions.util.MailGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private CriterioWebsiteRepository criterioWebsiteRepository;
    @Autowired
    private MarcaRepository marcaRepository;
    @Autowired
    private CotizacionService cotizacionService;
    @Autowired
    private CriterioopcionRepository criterioopcionRepository;
    @Autowired
    private MailGenerator mailGenerator;

    public ApiResponse<List<ProductoWebsiteDto>> getProductsToBanner() {
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by("id").descending());
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
        PageRequest pageRequest = PageRequest.of(0, 12, Sort.by(Sort.Direction.DESC, "id"));
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

    public ApiResponse<Map<String, Object>> getProductsCategory(Integer idCategory, String brand, String strPriceRange, Integer sortBy, Integer page, Integer inf) {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("records", productoWebsiteFilterRepository.getAll(idCategory, brand, strPriceRange, sortBy, page));

        if (inf == 1) {
            objectMap.put("totalRecords", productoWebsiteFilterRepository.getTotalRecords(idCategory, brand, strPriceRange));
        }

        return new ApiResponse<>(true, "Ok", objectMap);
    }

    public ApiResponse<List<ProductoWebsiteDto>> getSimilarProducts(Integer id) {
        Integer idCategory = productoWebsiteRepository.getIdCategoryByIdProduct(id);
        PageRequest pageRequest = PageRequest.of(0, 12);
        return new ApiResponse<>(true, "Ok", productoWebsiteRepository.getSimilarProducts(idCategory, id, pageRequest));
    }

    public ApiResponse<List<CriterioWebsiteDto>> getCriteria() {
        List<CriterioWebsiteDto> criterioWebsiteDtoList = criterioWebsiteRepository.getAll();
        criterioWebsiteDtoList.forEach(criterioWebsiteDto -> criterioWebsiteDto.setCriterioopcionDtoList(criterioWebsiteRepository.listByIdCriteria(criterioWebsiteDto.getId())));

        return new ApiResponse<>(true, "Ok", criterioWebsiteDtoList);
    }

    public ApiResponse<List<ProductoWebsiteDto>> getProductsByCriteria(String idCriteriaValues) {
        return new ApiResponse<>(true, "Ok", productoWebsiteFilterRepository.getByCriteria(idCriteriaValues));
    }

    public ApiResponse<String> generateQuotation(CotizacionWebsiteDto cotizacionWebsiteDto) {
        CotizacionDto cotizacion = new CotizacionDto();
        cotizacion.setOrigen((byte) 2);

        String[] arrCriterioopcionList = cotizacionWebsiteDto.getCriterioopcionList().split(",");

        List<CotizacionCriterioopcion> cotizacionCriterioopcionList = new ArrayList<>();
        for (int i = 0; i < arrCriterioopcionList.length; i++) {
            CotizacionCriterioopcion cotizacionCriterioopcion = new CotizacionCriterioopcion();
            cotizacionCriterioopcion.setCriterioopcion(criterioopcionRepository.findById(Integer.parseInt(arrCriterioopcionList[i])).orElse(null));
            cotizacionCriterioopcionList.add(cotizacionCriterioopcion);
        }
        cotizacion.setCotizacionCriterioopcionList(cotizacionCriterioopcionList);

        String[] arrProductoList = cotizacionWebsiteDto.getProductoList().split(",");

        List<CotizaciondetalleDto> cotizaciondetalleDtoList = new ArrayList<>();
        for (int i = 0; i < arrProductoList.length; i++) {
            CotizaciondetalleDto cotizaciondetalleDto = new CotizaciondetalleDto();
            cotizaciondetalleDto.setIdProducto(Integer.parseInt(arrProductoList[i]));
            cotizaciondetalleDto.setCantidad((short) 1);
            cotizaciondetalleDto.setPrecio(productoWebsiteRepository.getPrice(cotizaciondetalleDto.getIdProducto()));
            cotizaciondetalleDtoList.add(cotizaciondetalleDto);
        }
        cotizacion.setCotizaciondetalleList(cotizaciondetalleDtoList);

        ApiResponse<String> apiResponse = cotizacionService.save(cotizacion);

        mailGenerator.sendMessageHTML(cotizacionWebsiteDto.getEmail(), HTMLTemplate.MESSAGE01);

        return apiResponse;
    }
}

package com.apsolutions.service;

import com.apsolutions.dto.indicator.ProductoIndicatorDto;
import com.apsolutions.repository.*;
import com.apsolutions.repository.custom.ProductoIndicadorRepository;
import com.apsolutions.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IndicadorService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private ProductoIndicadorRepository productoIndicadorRepository;
    @Autowired
    private CotizacionRepository cotizacionRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProveedorRepository proveedorRepository;

    public ApiResponse<Map<String, Object>> filtros() {
        Map<String, Object> objectMap = new HashMap<>();

        objectMap.put("categoriaList", categoriaRepository.list());

        return new ApiResponse<>(true, "Ok", objectMap);
    }

    public ApiResponse<List<ProductoIndicatorDto>> stockproductos(int idCategoria, int valOrden) {
        List<ProductoIndicatorDto> productoIndicatorDtoList = productoIndicadorRepository.getStockListForChart(getCurrentYear(), idCategoria, valOrden);

        for (int i = 0; i < productoIndicatorDtoList.size(); i++) {
            productoIndicatorDtoList.get(i).setInitial(0);
            if (i == 0) {
                productoIndicatorDtoList.get(i).setSelected(true);
                productoIndicatorDtoList.get(i).setSliced(true);
            }
        }

        return new ApiResponse<>(true, "Ok", productoIndicatorDtoList);
    }

    public ApiResponse<Map<String, Object>> cotizaciones() {
        int year = getCurrentYear();
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("name", year);
        objectMap.put("data", cotizacionRepository.cotizacionesGeneradas(year));
        return new ApiResponse<>(true, "Ok", objectMap);
    }

    private int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar.get(Calendar.YEAR);
    }

    public ApiResponse<Map<String, Object>> totales() {
        Map<String, Object> objectMap = new HashMap<>();

        objectMap.put("producto", productoRepository.getTotalRegistros());
        objectMap.put("cliente", clienteRepository.getTotalRegistros());
        objectMap.put("proveedor", proveedorRepository.getTotalRegistros());
        objectMap.put("cotizacion", cotizacionRepository.getTotalRegistros());

        return new ApiResponse<>(true, "Ok", objectMap);
    }
}

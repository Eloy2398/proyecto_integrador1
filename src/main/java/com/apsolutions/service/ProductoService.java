package com.apsolutions.service;

import com.apsolutions.dto.ProductoDto;
import com.apsolutions.exception.CsException;
import com.apsolutions.model.Producto;
import com.apsolutions.repository.ProductoRepository;
import com.apsolutions.util.ApiResponse;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public ApiResponse<String> save(Producto producto) {
        try {
            checkValidations(producto.getNombre(), producto.getMarca().getId(), 0);
            productoRepository.save(producto);
            return new ApiResponse<>(true, "Se registró correctamente");
        } catch (DataIntegrityViolationException e) {
            throw new CsException("Error de integridad de datos");
        }
    }

    public ApiResponse<String> edit(Integer id, Producto producto) {
        try {
            producto.setId(id);
            checkValidations(producto.getNombre(), producto.getMarca().getId(), producto.getId());
            productoRepository.save(producto);
            return new ApiResponse<>(true, "Se modificó correctamente");
        } catch (DataIntegrityViolationException e) {
            throw new CsException("Error de integridad de datos");
        }
    }

    private void checkValidations(String nombre, int idmarca, int id) {
        Optional<Producto> optionalProduct = productoRepository.existsByNameAndBrand(nombre, idmarca, id);
        if (optionalProduct.isPresent()) {
            throw new CsException("El producto " + nombre + " ya se encuentro registrado");
        }
    }

    public ApiResponse<List<ProductoDto>> list() {
        return new ApiResponse<>(true, "OK", productoRepository.list());
    }
}

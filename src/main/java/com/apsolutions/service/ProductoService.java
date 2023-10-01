package com.apsolutions.service;

import com.apsolutions.dto.ProductoDto;
import com.apsolutions.dto.ProductoListDto;
import com.apsolutions.exception.CsException;
import com.apsolutions.mapper.ProductoMapper;
import com.apsolutions.model.Producto;
import com.apsolutions.repository.ProductoCriterioopcionRepository;
import com.apsolutions.repository.ProductoRepository;
import com.apsolutions.util.ApiResponse;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;
    private final ProductoCriterioopcionRepository productoCriterioopcionRepository;

    public ProductoService(ProductoRepository productoRepository, ProductoMapper productoMapper, ProductoCriterioopcionRepository productoCriterioopcionRepository) {
        this.productoRepository = productoRepository;
        this.productoMapper = productoMapper;
        this.productoCriterioopcionRepository = productoCriterioopcionRepository;
    }

    @Transactional
    public ApiResponse<String> save(ProductoDto productoDto) {
        productoDto.setId(0);
        processSaved(productoDto);
        return new ApiResponse<>(true, "Se registró correctamente");
    }

    @Transactional
    public ApiResponse<String> edit(Integer id, ProductoDto productoDto) {
        productoDto.setId(id);
        processSaved(productoDto);
        return new ApiResponse<>(true, "Se modificó correctamente");
    }

    private void processSaved(ProductoDto productoDto) {
        try {
            checkValidations(productoDto.getNombre(), productoDto.getMarca().getId(), productoDto.getId());
            Producto producto = productoRepository.save(productoMapper.toEntity(productoDto));

            productoDto.getProductoCriterioopcionList().forEach(productoCriterioopcion -> {
                productoCriterioopcion.setProducto(producto);
                productoCriterioopcionRepository.save(productoCriterioopcion);
            });

        } catch (DataIntegrityViolationException | JpaObjectRetrievalFailureException e) {
            throw new CsException("Error de integridad de datos " + e.getMessage());
        }
    }

    private void checkValidations(String nombre, int idmarca, int id) {
        Optional<Producto> optionalProduct = productoRepository.existsByNameAndBrand(nombre, idmarca, id);
        if (optionalProduct.isPresent()) {
            throw new CsException("El producto " + nombre + " ya se encuentro registrado");
        }
    }

    public ApiResponse<String> delete(Integer id) {
        if (!productoRepository.existsById(id)) {
            throw new CsException("No se encontró registro");
        }

        if (productoRepository.getStock(id) > 0) {
            throw new CsException("No se puede eliminar un producto con stock");
        }

        productoRepository.updateStatus(false, id);
        return new ApiResponse<>(true, "Se eliminó correctamente");
    }

    public ApiResponse<List<ProductoListDto>> list() {
        return new ApiResponse<>(true, "OK", productoRepository.list());
    }
}

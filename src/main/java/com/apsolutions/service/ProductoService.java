package com.apsolutions.service;

import com.apsolutions.dto.ProductoDto;
import com.apsolutions.dto.ProductoListDto;
import com.apsolutions.exception.CsException;
import com.apsolutions.mapper.ProductoMapper;
import com.apsolutions.model.Caracteristica;
import com.apsolutions.model.Producto;
import com.apsolutions.model.ProductoCaracteristicaPK;
import com.apsolutions.model.ProductoCriterioopcion;
import com.apsolutions.repository.CaracteristicaRepository;
import com.apsolutions.repository.ProductoCaracteristicaRepository;
import com.apsolutions.repository.ProductoCriterioopcionRepository;
import com.apsolutions.repository.ProductoRepository;
import com.apsolutions.util.ApiResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    @Autowired
    private ProductoMapper productoMapper;
    @Autowired
    private CaracteristicaRepository caracteristicaRepository;
    @Autowired
    private ProductoCriterioopcionRepository productoCriterioopcionRepository;
    @Autowired
    private ProductoCaracteristicaRepository productoCaracteristicaRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
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
            checkValidations(productoDto);

            if (productoDto.getId() > 0) {
                productoCriterioopcionRepository.updateStatusByIdProducto(false, productoDto.getId());
                productoCaracteristicaRepository.deleteByIdProducto(productoDto.getId());
            }

            Producto producto = productoRepository.save(productoMapper.toEntity(productoDto));

            productoDto.getProductoCriterioopcionList().forEach(productoCriterioopcion -> {
                Optional<ProductoCriterioopcion> optionalProductoCriterioopcion = productoCriterioopcionRepository.existsByIdProductoAndIdCriterioopcion(producto.getId(), productoCriterioopcion.getCriterioopcion().getId());
                if (optionalProductoCriterioopcion.isPresent()) {
                    productoCriterioopcionRepository.updateStatus(true, optionalProductoCriterioopcion.get().getId());
                } else {
                    productoCriterioopcion.setProducto(producto);
                    productoCriterioopcion.setEstado(true);
                    productoCriterioopcionRepository.save(productoCriterioopcion);
                }
            });

            productoDto.getProductoCaracteristicaList().forEach(productoCaracteristica -> {
                ProductoCaracteristicaPK pk = new ProductoCaracteristicaPK();
                pk.setIdProducto(producto.getId());

                Optional<Caracteristica> optionalCaracteristica = caracteristicaRepository.existsByName(productoCaracteristica.getCaracteristica().getNombre());
                if (optionalCaracteristica.isPresent()) {
                    productoCaracteristica.setCaracteristica(optionalCaracteristica.get());
                    pk.setIdCaracteristica(productoCaracteristica.getCaracteristica().getId());
                } else {
                    pk.setIdCaracteristica(caracteristicaRepository.save(productoCaracteristica.getCaracteristica()).getId());
                }

                productoCaracteristica.setId(pk);
                productoCaracteristicaRepository.save(productoCaracteristica);
            });

        } catch (DataIntegrityViolationException | JpaObjectRetrievalFailureException e) {
            throw new CsException("Error de integridad de datos " + e.getMessage());
        }
    }

    private void checkValidations(ProductoDto productoDto) {
        Optional<Producto> optionalProduct;

        optionalProduct = productoRepository.existsByNameAndBrand(productoDto.getNombre(), productoDto.getMarca().getId(), productoDto.getId());
        if (optionalProduct.isPresent()) {
            throw new CsException("El producto " + productoDto.getNombre() + " ya se encuentro registrado");
        }

        if (!productoDto.getCodigo().isEmpty()) {
            optionalProduct = productoRepository.existsByCode(productoDto.getCodigo(), productoDto.getId());
            if (optionalProduct.isPresent()) {
                throw new CsException("El código " + productoDto.getCodigo() + " ya se encuentro registrado");
            }
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

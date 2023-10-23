package com.apsolutions.service;

import com.apsolutions.dto.MovimientoDto;
import com.apsolutions.dto.ProductoDto;
import com.apsolutions.dto.ProductoListDto;
import com.apsolutions.exception.CsException;
import com.apsolutions.mapper.ProductoMapper;
import com.apsolutions.model.*;
import com.apsolutions.repository.*;
import com.apsolutions.util.ApiResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

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
    @Autowired
    private MarcaRepository marcaRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private MovimientoService movimientoService;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Transactional
    public ApiResponse<String> save(ProductoDto productoDto) {
        if (productoDto.getId() == null) {
            productoDto.setId(0);
        }

        processSaved(productoDto);

        return new ApiResponse<>(true, "Se " + (productoDto.getId() > 0 ? "modificó" : "registró") + " correctamente");
    }

    private void processSaved(ProductoDto productoDto) {
        try {
            checkValidations(productoDto);

            if (productoDto.getId() > 0) {
                productoCriterioopcionRepository.updateStatusByIdProducto(false, productoDto.getId());
                productoCaracteristicaRepository.deleteByIdProducto(productoDto.getId());
            }

            productoDto.setEstado(true);
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

            productoDto.getProductoCaracteristicaList().forEach(productoCaracteristicaDto -> {
                ProductoCaracteristica productoCaracteristica = new ProductoCaracteristica();
                productoCaracteristica.setValor(productoCaracteristicaDto.getValor());

                ProductoCaracteristicaPK pk = new ProductoCaracteristicaPK();
                pk.setIdProducto(producto.getId());

                Optional<Caracteristica> optionalCaracteristica = caracteristicaRepository.existsByName(productoCaracteristicaDto.getNombre());
                if (optionalCaracteristica.isPresent()) {
                    productoCaracteristica.setCaracteristica(optionalCaracteristica.get());
                    pk.setIdCaracteristica(productoCaracteristica.getCaracteristica().getId());
                } else {
                    productoCaracteristica.setCaracteristica(new Caracteristica());
                    productoCaracteristica.getCaracteristica().setNombre(productoCaracteristicaDto.getNombre());
                    pk.setIdCaracteristica(caracteristicaRepository.save(productoCaracteristica.getCaracteristica()).getId());
                }

                productoCaracteristica.setId(pk);
                productoCaracteristicaRepository.save(productoCaracteristica);
            });

            if (producto.getStock() > 0 && !(productoDto.getId() > 0)) {
                MovimientoDto movimientoDto = new MovimientoDto();
                movimientoDto.setFecha(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
                movimientoDto.setTipo((byte) 1);
                movimientoDto.setDescripcion("Inventario inicial");

                Movimientodetalle movimientodetalle = new Movimientodetalle();
                movimientodetalle.setProducto(producto);
                movimientodetalle.setPrecio(producto.getPrecio());
                movimientodetalle.setCantidad(producto.getStock());
                List<Movimientodetalle> list = new ArrayList<>();
                list.add(movimientodetalle);

                movimientoDto.setMovimientodetalleList(list);
                movimientoService.save(movimientoDto);
            }

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

    @Transactional
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

    public ApiResponse<Map<String, Object>> loadExtraData() {
        Map<String, Object> data = new HashMap<>();
        data.put("categoriaList", categoriaRepository.list());
        data.put("marcaList", marcaRepository.list());
        return new ApiResponse<>(true, "Ok", data);
    }

    public ApiResponse<ProductoDto> read(Integer id) {
        ProductoDto productoDto = productoMapper.toDto(productoRepository.findById(id).orElse(null));
        productoDto.setProductoCaracteristicaList(productoCaracteristicaRepository.findByIdProducto(id));

        return new ApiResponse<>(true, "Ok", productoDto);
    }
}

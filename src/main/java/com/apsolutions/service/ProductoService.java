package com.apsolutions.service;

import com.apsolutions.dto.*;
import com.apsolutions.dto.query.ProductoQueryDto;
import com.apsolutions.exception.CsException;
import com.apsolutions.mapper.CriterioMapper;
import com.apsolutions.mapper.CriterioopcionMapper;
import com.apsolutions.mapper.ProductoMapper;
import com.apsolutions.model.*;
import com.apsolutions.repository.*;
import com.apsolutions.repository.custom.ProductoFilterRepository;
import com.apsolutions.util.ApiResponse;
import com.apsolutions.util.FileStorage;
import com.apsolutions.util.Global;
import com.apsolutions.util.URLNormalizer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    @Autowired
    private ProductoFilterRepository productoFilterRepository;
    @Autowired
    private ProductoMapper productoMapper;
    @Autowired
    private CriterioMapper criterioMapper;
    @Autowired
    private CriterioopcionMapper criterioopcionMapper;
    @Autowired
    private CaracteristicaRepository caracteristicaRepository;
    @Autowired
    private ProductoCaracteristicaRepository productoCaracteristicaRepository;
    @Autowired
    private MarcaRepository marcaRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private MovimientoService movimientoService;
    @Autowired
    private CriterioRepository criterioRepository;
    @Autowired
    private CriterioopcionRepository criterioopcionRepository;
    @Autowired
    private ProductoCriterioopcionRepository productoCriterioopcionRepository;
    @Autowired
    private FileStorage fileStorage;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Transactional
    public ApiResponse<String> save(ProductoDto productoDto) {
        if (productoDto.getId() == null) {
            productoDto.setId(0);
        }

        processSaved(productoDto);

        return new ApiResponse<>(true, productoDto.getId() > 0 ? Global.SUCCESSFUL_UPDATE_MESSAGE : Global.SUCCESSFUL_INSERT_MESSAGE);
    }

    private void processSaved(ProductoDto productoDto) {
        try {
            checkValidations(productoDto);

            String filenameImage = "";

            if (productoDto.getId() > 0) {
                productoCriterioopcionRepository.updateStatusByIdProducto(false, productoDto.getId());
                productoCaracteristicaRepository.deleteByIdProducto(productoDto.getId());
                filenameImage = productoRepository.getImage(productoDto.getId());
                if (productoDto.getFile() == null) {
                    fileStorage.delete(filenameImage, Global.DIR_PRODUCTS);
                }
            }

            productoDto.setEstado(true);
            Producto productTmp = productoMapper.toEntity(productoDto);
            if (productTmp.getStock() == null) productTmp.setStock((short) 0);
            productTmp.setNombreUrl(URLNormalizer.encode(productTmp.getNombre()));
            productTmp.setImagen(fileStorage.upload(productoDto.getFile(), Global.DIR_PRODUCTS, filenameImage));
            Producto producto = productoRepository.save(productTmp);

            productoDto.getProductoCriterioopcionList().forEach(idCriterioopcion -> {
                Optional<ProductoCriterioopcion> optionalProductoCriterioopcion = productoCriterioopcionRepository.existsByIdProductoAndIdCriterioopcion(producto.getId(), idCriterioopcion);
                if (optionalProductoCriterioopcion.isPresent()) {
                    productoCriterioopcionRepository.updateStatus(true, optionalProductoCriterioopcion.get().getId());
                } else {
                    ProductoCriterioopcion productoCriterioopcion = new ProductoCriterioopcion();
                    productoCriterioopcion.setCriterioopcion(new Criterioopcion());
                    productoCriterioopcion.getCriterioopcion().setId(idCriterioopcion);
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

                MovimientodetalleDto movimientodetalleDto = new MovimientodetalleDto();
                movimientodetalleDto.setIdProducto(producto.getId());
                movimientodetalleDto.setPrecio(producto.getPrecio());
                movimientodetalleDto.setCantidad(producto.getStock());
                List<MovimientodetalleDto> list = new ArrayList<>();
                list.add(movimientodetalleDto);

                movimientoDto.setMovimientodetalleList(list);
                movimientoService.save(movimientoDto);
            }

        } catch (DataIntegrityViolationException | JpaObjectRetrievalFailureException e) {
            throw new CsException(Global.DATA_INTEGRITY_ERROR + e.getMessage());
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
            throw new CsException(Global.REGISTER_NOT_FOUND);
        }

        if (productoRepository.getStock(id) > 0) {
            throw new CsException("No se puede eliminar un producto con stock");
        }

        productoRepository.updateStatus(false, id);
        return new ApiResponse<>(true, Global.SUCCESSFUL_DELETE_MESSAGE);
    }

    public ApiResponse<List<ProductoListDto>> list(int tipo, String nombre, int idCategory, int idBrand) {
        List<ProductoListDto> productoListDtos = productoFilterRepository.filter(tipo, nombre, idCategory, idBrand);
        return new ApiResponse<>(true, "OK", productoListDtos);
    }

    public ApiResponse<Map<String, Object>> loadExtraData() {
        Map<String, Object> data = new HashMap<>();
        data.put("categoriaList", categoriaRepository.list());
        data.put("marcaList", marcaRepository.list());

        List<CriterioDto> criterioDtoList = criterioMapper.toDto(criterioRepository.list());
        criterioDtoList.forEach(criterioDto -> criterioDto.setCriterioopcionList(criterioopcionMapper.toEntity(criterioopcionRepository.listByIdCriterio(criterioDto.getId()))));

        data.put("criterioopcionList", criterioDtoList);

        return new ApiResponse<>(true, "Ok", data);
    }

    public ApiResponse<ProductoDto> read(Integer id) {
        Producto producto = productoRepository.findById(id).orElse(new Producto());
        ProductoDto productoDto = productoMapper.toDto(producto);
        productoDto.setProductoCaracteristicaList(productoCaracteristicaRepository.findByIdProducto(id));
        productoDto.setProductoCriterioopcionList(productoCriterioopcionRepository.listIdCriterioopcionByIdProducto(id));

        return new ApiResponse<>(true, "Ok", productoDto);
    }

    public ApiResponse<List<ProductoQueryDto>> search(String query) {
        return new ApiResponse<>(true, "Ok", productoRepository.search(query + "%"));
    }

    public ApiResponse<String> upload(MultipartFile file) {
        String uploadedFilename = fileStorage.upload(file, Global.DIR_PRODUCTS);
        return new ApiResponse<>(true, "File " + uploadedFilename + " uploaded successfully");
    }
}

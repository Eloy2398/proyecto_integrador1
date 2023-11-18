package com.apsolutions.dto.website;

import com.apsolutions.dto.ProductoCaracteristicaDto;

import java.math.BigDecimal;
import java.util.List;

public class ProductoDto {

    private Integer id;
    private String codigo;
    private String nombre;
    private String nombreUrl;
    private String descripcion;
    private String categoriaNombre;
    private String marcaNombre;
    private BigDecimal precio;
    private Short stock;
    private String imagen;
    private List<ProductoCaracteristicaDto> productoCaracteristicaList;

    public ProductoDto(Integer id, String nombre, String nombreUrl, String descripcion, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.nombreUrl = nombreUrl;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public ProductoDto(Integer id, String nombre, String nombreUrl, BigDecimal precio, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.nombreUrl = nombreUrl;
        this.precio = precio;
        this.imagen = imagen;
    }

    public ProductoDto(Integer id, String codigo, String nombre, String descripcion, String categoriaNombre, String marcaNombre, BigDecimal precio, Short stock, String imagen) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoriaNombre = categoriaNombre;
        this.marcaNombre = marcaNombre;
        this.precio = precio;
        this.stock = stock;
        this.imagen = imagen;
    }

    public Integer getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNombreUrl() {
        return nombreUrl;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public String getMarcaNombre() {
        return marcaNombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public Short getStock() {
        return stock;
    }

    public String getImagen() {
        return imagen;
    }

    public List<ProductoCaracteristicaDto> getProductoCaracteristicaList() {
        return productoCaracteristicaList;
    }

    public void setProductoCaracteristicaList(List<ProductoCaracteristicaDto> productoCaracteristicaList) {
        this.productoCaracteristicaList = productoCaracteristicaList;
    }
}

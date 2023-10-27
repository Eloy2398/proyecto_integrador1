package com.apsolutions.dto;

import com.apsolutions.model.Categoria;
import com.apsolutions.model.Marca;

import java.math.BigDecimal;
import java.util.List;

public class ProductoDto {
    private Integer id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private Categoria categoria;
    private Marca marca;
    private BigDecimal precio;
    private Short stock;
    private String imagen;
    private Boolean estado;
    private List<Integer> productoCriterioopcionList;

    private List<ProductoCaracteristicaDto> productoCaracteristicaList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Short getStock() {
        return stock;
    }

    public void setStock(Short stock) {
        this.stock = stock;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public List<Integer> getProductoCriterioopcionList() {
        return productoCriterioopcionList;
    }

    public void setProductoCriterioopcionList(List<Integer> productoCriterioopcionList) {
        this.productoCriterioopcionList = productoCriterioopcionList;
    }

    public List<ProductoCaracteristicaDto> getProductoCaracteristicaList() {
        return productoCaracteristicaList;
    }

    public void setProductoCaracteristicaList(List<ProductoCaracteristicaDto> productoCaracteristicaList) {
        this.productoCaracteristicaList = productoCaracteristicaList;
    }
}

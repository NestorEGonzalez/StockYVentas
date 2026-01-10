package com.almacen.stockyventas.model;

import java.math.BigDecimal;

import org.jspecify.annotations.Nullable;

import com.almacen.stockyventas.exception.ProductoConNombreInvalidoError;
import com.almacen.stockyventas.exception.ValorInvalidoError;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
@Entity

public class Producto {
    @Id
    @GeneratedValue
    
    private Long id;
    
    private String nombre;
    private Integer stock;
    private BigDecimal precio;
    
    public Producto(){}

    public Producto(String nombre, Integer stock, BigDecimal precio) {
        validarNombre(nombre);
        validarStock(stock);
        validarPrecio(precio);
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
    }
    
    public @Nullable Long getId() {
        return id;
    }

    public String getNombre(){
        return this.nombre;
    }

    public Integer getStock(){
        return this.stock;
    }

    public BigDecimal getPrecio(){
        return this.precio;
    }

    public void setNombre(String nombre) {
        validarNombre(nombre);
        this.nombre = nombre;
    }

    private void validarNombre(String nombre) {
        if(nombre == null){
            throw new ProductoConNombreInvalidoError(ProductoConNombreInvalidoError.ERROR_NOMBRE_NULO);
        }
        if (nombre =="") {
            throw new ProductoConNombreInvalidoError(ProductoConNombreInvalidoError.ERROR_NOMBRE_VACIO);
        }
    }

    public void setStock(Integer stock) {
        validarStock(stock);
        this.stock = stock;
    }

    private void validarStock(Integer stock) {
        if (stock == null) {
            throw new ValorInvalidoError(ValorInvalidoError.ERROR_STOCK_NULO);
        }
        if (stock < 0) {
            throw new ValorInvalidoError(ValorInvalidoError.ERROR_STOCK_NEGATIVO);
        }
    }

    public void setPrecio(BigDecimal precio) {
        validarPrecio(precio);
        this.precio = precio;
    }

    private void validarPrecio(BigDecimal precio) {
        
        if (precio == null) {
            throw new ValorInvalidoError(ValorInvalidoError.ERROR_PRECIO_NULO);
        }

        if (precio.compareTo(BigDecimal.ZERO)<0) {
            throw new ValorInvalidoError(ValorInvalidoError.ERROR_PRECIO_NEGATIVO);
        }
    }

    public void bajarStock(Integer cantidad) {
        validarCantidadNoNulo(cantidad);
        validarStockSuficiente(cantidad);
        this.stock -= cantidad;
    }
    
    private void validarCantidadNoNulo(Integer cantidad) {
        if (cantidad == null) {
            throw new ValorInvalidoError(ValorInvalidoError.ERROR_CANTIDAD_NULA);
        }
    }

    private void validarStockSuficiente(Integer cantidad) {
        
        if (this.stock < cantidad) {
            throw new ValorInvalidoError(ValorInvalidoError.ERROR_STOCK_INSUFICIENTE);
        }
        
    }

    public void reponerStock(Integer cantidad) {
        validarCantidadNoNulo(cantidad);
        this.stock += cantidad;
    }

}


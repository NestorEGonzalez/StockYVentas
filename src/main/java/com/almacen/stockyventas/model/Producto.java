package com.almacen.stockyventas.model;

import java.math.BigDecimal;

import org.jspecify.annotations.Nullable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity

public class Producto {
    @Id
    @GeneratedValue
    
    private Long id;
    
    private String nombre;
    private int stock;
    private BigDecimal precio;
    
    public Producto(String nombre, Integer stock, BigDecimal precio) {
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

    public void updateStock(int i) {
        this.stock -= i;
    }

    public void updatePrecio(BigDecimal precioNuevo) {
        this.precio = precioNuevo;
    }

}


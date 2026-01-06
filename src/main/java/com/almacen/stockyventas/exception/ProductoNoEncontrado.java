package com.almacen.stockyventas.exception;

public class ProductoNoEncontrado extends RuntimeException{
    public ProductoNoEncontrado(String mensaje){
        super(mensaje);
    }
}

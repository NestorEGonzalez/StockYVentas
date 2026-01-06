package com.almacen.stockyventas.exception;

public class ProductoConPreciokNegativo extends RuntimeException {

    public ProductoConPreciokNegativo(String mensaje){
        super(mensaje);
    }
}

package com.almacen.stockyventas.exception;

public class ProductoConStockNegativo  extends RuntimeException{
    public ProductoConStockNegativo(String mensaje){
        super(mensaje);
    }
}

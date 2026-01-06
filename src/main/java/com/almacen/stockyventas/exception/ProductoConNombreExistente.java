package com.almacen.stockyventas.exception;

public class ProductoConNombreExistente extends RuntimeException{

     public ProductoConNombreExistente(String mensaje){
        super(mensaje);
     }
}
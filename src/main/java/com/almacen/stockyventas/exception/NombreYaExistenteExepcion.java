package com.almacen.stockyventas.exception;

public class NombreYaExistenteExepcion extends RuntimeException{
    
    public static final String ERROR_NOMBRE_EXISTENTE = "El nombre del producto ya existe.";

    public NombreYaExistenteExepcion(String mensaje){
        super(mensaje);
    }
}

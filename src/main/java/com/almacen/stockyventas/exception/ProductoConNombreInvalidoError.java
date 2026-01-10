package com.almacen.stockyventas.exception;

public class ProductoConNombreInvalidoError extends RuntimeException{
   
    public static final String ERROR_NOMBRE_VACIO = "El nombre no puede estar vacío.";
    public static final String ERROR_NOMBRE_NULO = "El nombre no puede ser nulo.";
    
    public ProductoConNombreInvalidoError(String errorNombreInvalido) {
        super(errorNombreInvalido);
    }

    
}

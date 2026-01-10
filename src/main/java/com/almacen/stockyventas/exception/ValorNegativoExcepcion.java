package com.almacen.stockyventas.exception;

public class ValorNegativoExcepcion extends RuntimeException {
    
    public static final String ERROR_PRECIO_NEGATIVO = "El precio no puede ser negativo.";
    public static final String ERROR_STOCK_NEGATIVO = "El stock no puede ser negativo.";
    
    public ValorNegativoExcepcion(String mensaje){
        super(mensaje);
    }
    
}

package com.almacen.stockyventas.exception;

public class ValorInvalidoError extends RuntimeException{
    public static final String ERROR_STOCK_NEGATIVO= "El stock no puede ser negativo.";
    public static final String ERROR_STOCK_NULO= "El stock no puede ser nulo.";
    public static final String ERROR_PRECIO_NEGATIVO= "El precio no puede ser negativo.";
    public static final String ERROR_PRECIO_NULO= "El precio no puede ser nulo.";
    public static final String ERROR_STOCK_INSUFICIENTE = "Stock insuficiente.";
    public static final String ERROR_CANTIDAD_NULA ="LA cantidad ingresada es nula.";
    
    public ValorInvalidoError(String errorValorInvalido) {
        super(errorValorInvalido);
    }
}

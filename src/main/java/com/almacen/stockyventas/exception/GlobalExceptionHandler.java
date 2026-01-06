package com.almacen.stockyventas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ProductoConNombreExistente.class)
    public ResponseEntity<String> errorDeNombreDuplicado(ProductoConNombreExistente ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(ProductoConStockNegativo.class)
    public ResponseEntity<String> errorStockNegativo(ProductoConStockNegativo ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(ProductoConPreciokNegativo.class)
    public ResponseEntity<String> errorPrecioNegativo(ProductoConPreciokNegativo ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}

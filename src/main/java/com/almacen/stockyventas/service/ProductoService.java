package com.almacen.stockyventas.service;

import com.almacen.stockyventas.exception.*;
import java.math.BigDecimal;


import org.springframework.stereotype.Service;

import com.almacen.stockyventas.model.Producto;
import com.almacen.stockyventas.repository.ProductoRepository;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository){
        this.productoRepository=productoRepository;
    }

    public Producto creaProducto(String nombre, int stock, BigDecimal precio){
        verificarNombreInexistente(nombre);
        verificarStockNegativo(stock);
        verificarPrecioNegativo(precio);

        Producto producto = new Producto(nombre,stock,precio);
        
        return productoRepository.save(producto);
    }

    private void verificarPrecioNegativo(BigDecimal precio) {
        BigDecimal cero = new BigDecimal(0);
        if (precio.compareTo(cero)<0) {
            throw new ValorNegativoExcepcion(ValorNegativoExcepcion.ERROR_PRECIO_NEGATIVO);
        }
    }

    private void verificarStockNegativo(int stock) {
        if (stock<0) {
            throw new ValorNegativoExcepcion(ValorNegativoExcepcion.ERROR_STOCK_NEGATIVO);
        }
    }

    private void verificarNombreInexistente(String nombre) {
        if (productoRepository.findOneByNombre(nombre)) {
            throw new NombreYaExistenteExepcion(NombreYaExistenteExepcion.ERROR_NOMBRE_EXISTENTE);
        }
    }
    
    
}

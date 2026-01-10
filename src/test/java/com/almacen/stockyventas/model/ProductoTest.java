package com.almacen.stockyventas.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.almacen.stockyventas.BaseTest;
import com.almacen.stockyventas.exception.ProductoConNombreInvalidoError;
import com.almacen.stockyventas.exception.ValorInvalidoError;


public class ProductoTest extends BaseTest {
    
    @Test
    void test01_sePuedeCrearUnProducto(){
        Producto producto1 = new Producto();
        producto1.setNombre("Termo");
        producto1.setStock(5);
        producto1.setPrecio(new BigDecimal(70.5));

        Producto producto2 = new Producto("Mate",4,(new BigDecimal(50.6)));

        assertEquals(producto1.getNombre(), "Termo");
        assertEquals(producto1.getStock(), 5);
        assertEquals(producto1.getPrecio(), (new BigDecimal(70.5)));
        assertEquals(producto2.getNombre(), "Mate");
        assertEquals(producto2.getStock(),4);
        assertEquals(producto2.getPrecio(), (new BigDecimal(50.6)));
    }

    @Test
    void test02_sePuedeCambiarLosDatosDeUnProducto(){
        Producto producto = new Producto("Mate",4,(new BigDecimal(50.6)));

        assertEquals(producto.getNombre(), "Mate");
        assertEquals(producto.getStock(),4);
        assertEquals(producto.getPrecio(), (new BigDecimal(50.6)));

        producto.setNombre("Termo");
        producto.setStock(5);
        producto.setPrecio(new BigDecimal(70.5));
        
        assertEquals(producto.getNombre(), "Termo");
        assertEquals(producto.getStock(), 5);
        assertEquals(producto.getPrecio(), (new BigDecimal(70.5)));

    }

    @Test
    void test03_unProductoNoPuedeTenerNombreVacioONulo(){
        Exception nombreVacio = assertThrows(ProductoConNombreInvalidoError.class, ()->{
            new Producto("",5,(new BigDecimal(50)));
        });

        Exception nombreNulo = assertThrows(ProductoConNombreInvalidoError.class, ()->{
            new Producto(null,5, (new BigDecimal(60)));
        });

        assertEquals(nombreVacio.getMessage(), ProductoConNombreInvalidoError.ERROR_NOMBRE_VACIO);
        assertEquals(nombreNulo.getMessage(), ProductoConNombreInvalidoError.ERROR_NOMBRE_NULO);

    }

    @Test
    void test04_unProductoNoPuedeTenerStockOPRecioNegativoONulo(){
        Exception stockNegativo = assertThrows(ValorInvalidoError.class,()->{
            new Producto("Termo", (-1), (new BigDecimal(50)));
        });

        Exception stockNulo = assertThrows(ValorInvalidoError.class,()->{
            new Producto("Termo", null, (new BigDecimal(50)));
        });
        
        Exception precioNegativo = assertThrows(ValorInvalidoError.class,()->{
            new Producto("Termo", 1, (new BigDecimal(-50)));
        });

        Exception precioNulo = assertThrows(ValorInvalidoError.class,()->{
            new Producto("Termo", 10, null);
        });

        assertEquals(stockNegativo.getMessage(), ValorInvalidoError.ERROR_STOCK_NEGATIVO);
        assertEquals(stockNulo.getMessage(),ValorInvalidoError.ERROR_STOCK_NULO);
        assertEquals(precioNegativo.getMessage(),ValorInvalidoError.ERROR_PRECIO_NEGATIVO);
        assertEquals(precioNulo.getMessage(),ValorInvalidoError.ERROR_PRECIO_NULO);
    }

    @Test
    void test05_unProductoPuedeModificarSuStockEnUnaCantidad(){
        Producto producto = new Producto("Mate",4,(new BigDecimal(50.6)));
        
        producto.bajarStock(2);
        assertEquals(producto.getStock(), 2);
        
        producto.reponerStock(3);
        assertEquals(producto.getStock(), 5);
    }

    @Test
    void test06_noSePuedeBajarMasStockDelDisponible(){
        Producto producto = new Producto("Mate",4,(new BigDecimal(50.6)));

        Exception stockInsuficiente = assertThrows(ValorInvalidoError.class,()->{
            producto.bajarStock(5);
        });

        Exception cantidadNula = assertThrows(ValorInvalidoError.class, ()->{
            producto.bajarStock(null);
        });

        assertEquals(stockInsuficiente.getMessage(),ValorInvalidoError.ERROR_STOCK_INSUFICIENTE);
        assertEquals(cantidadNula.getMessage(),ValorInvalidoError.ERROR_CANTIDAD_NULA);

    }
}

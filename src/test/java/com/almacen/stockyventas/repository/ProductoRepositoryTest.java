package com.almacen.stockyventas.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import com.almacen.stockyventas.model.Producto;

@DataJpaTest

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class ProductoRepositoryTest extends AbstractTest{
    
    @Autowired 
    ProductoRepository productoRepository;
    
    String nombre;
    Integer stock;
    BigDecimal precio;
    Producto producto;
    Producto productoGuardado;

    @BeforeEach
    void setUp(){
        nombre = "Producto de prueba";
        stock = 5;
        precio =new BigDecimal("105.4");
        producto = new Producto(nombre, stock, precio);
        productoGuardado = productoRepository.save(producto);

    }
    @AfterEach
    void limpiar(){
        productoRepository.deleteAll();

    }

    @Test
    void crearUnProductoYGuardarlo(){

        assertNotNull(productoGuardado.getId());
                
    }

    @Test
    void obtenerLosDatosDeUnProductoGuardado(){

        assertEquals(producto.getId(), productoRepository.findById(productoGuardado.getId()).get().getId());
        assertEquals(producto.getNombre(), productoRepository.findById(productoGuardado.getId()).get().getNombre());
        assertEquals(producto.getStock(), productoRepository.findById(productoGuardado.getId()).get().getStock());
        assertEquals(producto.getPrecio(), productoRepository.findById(productoGuardado.getId()).get().getPrecio());
    }
    
    @Test
    void eliminarUnProductoGuardado(){

        productoRepository.delete(productoGuardado);

        assertTrue(productoRepository.findById(productoGuardado.getId()).isEmpty());
    }

    @Test
    void modificarUnProductoGuardado(){

        assertEquals(producto.getId(), productoRepository.findById(productoGuardado.getId()).get().getId());
        assertEquals(producto.getNombre(), productoRepository.findById(productoGuardado.getId()).get().getNombre());
        assertEquals(producto.getStock(), productoRepository.findById(productoGuardado.getId()).get().getStock());
        assertEquals(producto.getPrecio(), productoRepository.findById(productoGuardado.getId()).get().getPrecio());

        Producto productoModificado = productoRepository.findById(producto.getId()).get();
        BigDecimal precioNuevo = new BigDecimal(500.4);
        productoModificado.updateStock(2);
        productoModificado.updatePrecio(precioNuevo);
        productoRepository.save(productoModificado);
        assertEquals(producto.getId(), productoRepository.findById(productoGuardado.getId()).get().getId());
        assertEquals(producto.getNombre(), productoRepository.findById(productoGuardado.getId()).get().getNombre());
        assertEquals(3, productoRepository.findById(productoGuardado.getId()).get().getStock());
        assertEquals(precioNuevo, productoRepository.findById(productoGuardado.getId()).get().getPrecio());
        
    }
    

}

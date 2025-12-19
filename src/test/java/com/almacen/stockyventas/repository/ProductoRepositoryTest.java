package com.almacen.stockyventas.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.almacen.stockyventas.model.Producto;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class ProductoRepositoryTest {
    
    @Container static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
        .withDatabaseName("testdb")
        .withUsername("test")
        .withPassword("test");
    
    @DynamicPropertySource static void configure(DynamicPropertyRegistry registry) { 
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword); 
    }

    @Autowired 
    ProductoRepository productoRepository;
    
    String nombre = "Producto de prueba";
    Integer stock = 5;
    BigDecimal precio =new BigDecimal(105.4);
    
    @Test
    void crearUnProductoYGuardarlo(){
        Producto p = new Producto(nombre,stock,precio);
        
        Producto productoGuardado = productoRepository.save(p);

        assertNotNull(productoGuardado.getId());
                
    }

    @Test
    void obtenerLosDatosDeUnProductoGuardado(){
        Producto p = new Producto(nombre,stock,precio);
        Producto productoGuardado = productoRepository.save(p);
        
        assertEquals(p.getId(), productoRepository.findById(productoGuardado.getId()).get().getId());
        assertEquals(p.getNombre(), productoRepository.findById(productoGuardado.getId()).get().getNombre());
        assertEquals(p.getStock(), productoRepository.findById(productoGuardado.getId()).get().getStock());
        assertEquals(p.getPrecio(), productoRepository.findById(productoGuardado.getId()).get().getPrecio());
    }
    
    @Test
    void eliminarUnProductoGuardado(){
        Producto p = new Producto(nombre,stock,precio);
        Producto productoGuardado = productoRepository.save(p);
        productoRepository.delete(productoGuardado);

        assertTrue(productoRepository.findById(productoGuardado.getId()).isEmpty());
    }

    @Test
    void modificarUnProductoGuardado(){
        Producto p = new Producto(nombre,stock,precio);
        Producto productoGuardado = productoRepository.save(p);
        assertEquals(p.getId(), productoRepository.findById(productoGuardado.getId()).get().getId());
        assertEquals(p.getNombre(), productoRepository.findById(productoGuardado.getId()).get().getNombre());
        assertEquals(p.getStock(), productoRepository.findById(productoGuardado.getId()).get().getStock());
        assertEquals(p.getPrecio(), productoRepository.findById(productoGuardado.getId()).get().getPrecio());

        Producto productoModificado = productoRepository.findById(p.getId()).get();
        BigDecimal precioNuevo = new BigDecimal(500.4);
        productoModificado.updateStock(2);
        productoModificado.updatePrecio(precioNuevo);
        productoRepository.save(productoModificado);
        assertEquals(p.getId(), productoRepository.findById(productoGuardado.getId()).get().getId());
        assertEquals(p.getNombre(), productoRepository.findById(productoGuardado.getId()).get().getNombre());
        assertEquals(3, productoRepository.findById(productoGuardado.getId()).get().getStock());
        assertEquals(precioNuevo, productoRepository.findById(productoGuardado.getId()).get().getPrecio());


        
    }

}

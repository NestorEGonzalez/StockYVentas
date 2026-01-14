package com.almacen.stockyventas.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.almacen.stockyventas.BaseTest;
import com.almacen.stockyventas.exception.NombreYaExistenteExepcion;
import com.almacen.stockyventas.model.Producto;


@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class ProductoRepositoryTest extends BaseTest{

    @Autowired
    ProductoRepository productoRepository;
    
    String termoString;
    String mateString;
    Integer termoStock;
    Integer mateStock;
    BigDecimal termoPrecio;
    BigDecimal matePrecio;
    Producto termo;
    Producto mate;
    
    @BeforeEach
    void setUp(){
        productoRepository.deleteAll();
        termoString = "Termo";
        mateString = "Mate";
        termoStock = 5;
        mateStock = 4;
        termoPrecio = new BigDecimal("105.4");
        matePrecio = new BigDecimal("70.5");
        termo = new Producto(termoString, termoStock, termoPrecio);
        mate = new Producto(mateString, mateStock, matePrecio);

    }

    @Test
    void test01_SePuedeCrearYGuardarUnProducto(){
        Producto termoGuardado = productoRepository.save(termo);
        assertNotNull(termoGuardado);
        assertEquals(termoGuardado.getNombre(), termo.getNombre());
        assertEquals(termoGuardado.getPrecio(), termo.getPrecio());
        assertEquals(termoGuardado.getStock(), termo.getStock());
    }

    @Test 
    void test02_sePuedeObtenerLosDatosDeUnProductoGuardado(){
        Producto termoGuardado = productoRepository.save(termo);
        assertEquals(termo.getNombre(), productoRepository.findById(termoGuardado.getId()).get().getNombre());
        assertEquals(termo.getStock(), productoRepository.findById(termoGuardado.getId()).get().getStock());
        assertEquals(termo.getPrecio(), productoRepository.findById(termoGuardado.getId()).get().getPrecio());
        assertEquals(1, productoRepository.count());

    }

    @Test
    void test03_sePuedenGuardarMasDeUnProducto(){
        Producto termoGuardado  = productoRepository.save(termo);
        Producto mateGuardado   = productoRepository.save(mate);

        assertEquals(2, productoRepository.count());
        assertEquals(termo.getNombre(), productoRepository.findById(termoGuardado.getId()).get().getNombre());
        assertEquals(termo.getStock(), productoRepository.findById(termoGuardado.getId()).get().getStock());
        assertEquals(termo.getPrecio(), productoRepository.findById(termoGuardado.getId()).get().getPrecio());
        assertEquals(mate.getNombre(), productoRepository.findById(mateGuardado.getId()).get().getNombre());
        assertEquals(mate.getStock(), productoRepository.findById(mateGuardado.getId()).get().getStock());
        assertEquals(mate.getPrecio(), productoRepository.findById(mateGuardado.getId()).get().getPrecio());

    }

    @Test
    void test04_sePuedeObtenerUnProductoPorSuNombre(){
        Producto termoGuardado  = productoRepository.save(termo);
        Producto mateGuardado   = productoRepository.save(mate);

        assertEquals(termoGuardado.getId(),productoRepository.findByNombre(termoString).get().getId());
        assertEquals(termoGuardado.getNombre(),productoRepository.findByNombre(termoString).get().getNombre());
        assertEquals(termoGuardado.getPrecio(), productoRepository.findByNombre(termoString).get().getPrecio());
        assertEquals(termoGuardado.getStock(), productoRepository.findByNombre(termoString).get().getStock());
        assertEquals(mateGuardado.getId(),productoRepository.findByNombre(mateString).get().getId());
        assertEquals(mateGuardado.getNombre(),productoRepository.findByNombre(mateString).get().getNombre());
        assertEquals(mateGuardado.getPrecio(), productoRepository.findByNombre(mateString).get().getPrecio());
        assertEquals(mateGuardado.getStock(), productoRepository.findByNombre(mateString).get().getStock());

    }

    @Test
    void test05_sePuedeModificarLosDatosDeUnProductoGuardado(){
        productoRepository.save(termo);
        productoRepository.save(mate);

        Producto termoGuardado  = productoRepository.findByNombre(termoString).get();
        Producto mateGuardado   = productoRepository.findByNombre(mateString).get();

        termoGuardado.setStock(15);
        mateGuardado.setPrecio(new BigDecimal("50"));

        productoRepository.save(termoGuardado);
        productoRepository.save(mateGuardado);

        assertEquals(15,productoRepository.findByNombre(termoString).get().getStock());
        assertEquals((new BigDecimal("50")), productoRepository.findByNombre(mateString).get().getPrecio());
        

    }

    @Test
    void test06_sePuedeEliminarUnProductoGuardado(){
        productoRepository.save(mate);
        productoRepository.save(termo);
        assertEquals(2,productoRepository.count());
        Long idABorrar = productoRepository.findByNombre(mateString).get().getId();
        productoRepository.deleteById(idABorrar);
        assertEquals(1,productoRepository.count());
        assertNotNull(productoRepository.findByNombre(termoString).get());

    }

    @Test
    void test07_noSePuedenGuardarDosProductosConElMismoNombre(){
        Producto termoGuardado = productoRepository.save(termo);
        mate.setNombre(termoString);
        assertEquals(1, productoRepository.count());
        assertEquals(termoGuardado.getId(),productoRepository.findById(termoGuardado.getId()).get().getId());
        assertEquals(termoGuardado.getNombre(),termoString);
        assertEquals(termoGuardado, termoGuardado);
        
    }

    @Test
    void test08_sePuedenObtenerTodosLosProductosGuardados(){
        productoRepository.save(termo);
        productoRepository.save(mate);

        List<Producto> productosGuardados = productoRepository.findAll();
        List <Producto> listaDeProductos = Arrays.asList(termo,mate);
        assertEquals(productosGuardados.size(),2);
        assertFalse(productosGuardados.isEmpty());
        assertTrue(productosGuardados.containsAll(listaDeProductos));
    }



    
    
}
    
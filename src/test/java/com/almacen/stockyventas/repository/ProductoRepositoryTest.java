package com.almacen.stockyventas.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.almacen.stockyventas.BaseTest;
import com.almacen.stockyventas.model.Producto;
import com.almacen.stockyventas.service.ProductoService;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class ProductoRepositoryTest extends BaseTest{

    /* @Autowired
    ProductoRepository productoRepository;
    
    @Autowired 
    ProductoService productoService; */
    private ProductoRepository productoRepository;
    private ProductoService productoService;

    public ProductoRepositoryTest(ProductoRepository productoRepository, ProductoService productoService){
        this.productoRepository = productoRepository;
        this.productoService = productoService;
    }

    
    String nombre;
    Integer stock;
    BigDecimal precio;
    Producto producto;
    Producto productoGuardado;

    @BeforeEach
    void setUp(){
        productoRepository.deleteAll();
        //nombre = "Producto de prueba";
        //stock = 5;
        //precio =new BigDecimal("105.4");
        //producto = new Producto(nombre, stock, precio);
        //productoGuardado = productoRepository.save(producto);

    }
    @AfterEach
    void clean(){
        
    }

    @Test
    void crearUnProductoYGuardarlo(){
        
        Producto producto = productoService.creaProducto("Termo", 5, (precio = new BigDecimal (70.5)));
        assertNotNull(producto.getId());
                
    }
}
    
/*     @Test
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

    @Test
    void noSePuedenGuardarProductosConPrecioOStockNegativo(){
        Exception excepcionPrecioNegativo = assertThrows(IllegalArgumentException.class,()->{
                new Producto("Negativo",5,new BigDecimal(-70));
        });

        Exception excepcionStockNegativo = assertThrows(IllegalArgumentException.class,()->{
                new Producto("StokNegativo",-1,new BigDecimal(5));
        });

        assertEquals(excepcionPrecioNegativo.getMessage(),ProductoMensajesDeError.ERROR_PRECIO_NEGATIVO);

        assertEquals(excepcionStockNegativo.getMessage(),ProductoMensajesDeError.ERROR_STOCK_NEGATIVO);
    }



}
 */
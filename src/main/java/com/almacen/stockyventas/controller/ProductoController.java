package com.almacen.stockyventas.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.almacen.stockyventas.model.Producto;
import com.almacen.stockyventas.service.ProductoService;

//import com.almacen.stockyventas.model.Producto;

@RestController
public class ProductoController {

    private final ProductoService productoService;
    
    @Autowired
    public ProductoController (ProductoService productoService){
        this.productoService = productoService;
    }


    @GetMapping("/productos")
    public ResponseEntity<List<Producto>> obtenerProductos(){
        List<Producto> productos = productoService.obtenerProductos();
        return new ResponseEntity<List<Producto>>(productos,HttpStatus.OK);
    }

    @PostMapping("/productos")
    public ResponseEntity<HttpStatus> crearProductos(@RequestBody Producto producto){
        productoService.crearProducto(producto);
        
        return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
    }

    @PutMapping("/producto/{id}")
    public ResponseEntity<HttpStatus> modificarProductos(@PathVariable long id,@RequestBody Producto producto){
        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/producto/{id}")
    public ResponseEntity<HttpStatus> borrarProducto(@PathVariable long id){
        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/productos/deleteAll")
    public ResponseEntity<HttpStatus> borrarTodos(){
        productoService.borrarTodos();
        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }
}

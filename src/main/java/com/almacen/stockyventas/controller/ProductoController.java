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

    @Autowired
    ProductoService productoService;

    /* @GetMapping("/productos")
    public ResponseEntity<List<Producto>> obtenerProductos(){
        return ResponseEntity.ok(productoService.obtenerProductos());
    }

    @PostMapping("/productos")
    public ResponseEntity<HttpStatus> crearProductos(@RequestBody String cadena){
        return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
    }

    @PutMapping("/producto/{id}")
    public ResponseEntity<HttpStatus> modificarProductos(@PathVariable long id,@RequestBody String cadena){
        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/producto/{id}")
    public ResponseEntity<HttpStatus> borrarProducto(@PathVariable long id){
        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    } */
}

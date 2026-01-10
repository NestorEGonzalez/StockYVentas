package com.almacen.stockyventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almacen.stockyventas.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{

    boolean findOneByNombre(String nombre);

    
}

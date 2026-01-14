package com.almacen.stockyventas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almacen.stockyventas.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{

    Optional<Producto> findByNombre(String string);

    
}

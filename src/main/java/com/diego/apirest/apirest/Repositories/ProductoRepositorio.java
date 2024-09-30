package com.diego.apirest.apirest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diego.apirest.apirest.Entities.Productos;

public interface ProductoRepositorio extends JpaRepository <Productos, Long>{

    
}

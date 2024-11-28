package com.principal.TPIWeb.Repositorio;


import org.springframework.data.jpa.repository.JpaRepository;

import com.principal.TPIWeb.Modelo.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{
    
}

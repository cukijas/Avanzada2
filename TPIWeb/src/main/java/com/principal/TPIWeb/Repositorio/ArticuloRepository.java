package com.principal.TPIWeb.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.principal.TPIWeb.Modelo.Articulo;

@Repository
public interface  ArticuloRepository extends JpaRepository<Articulo, Integer> {

}

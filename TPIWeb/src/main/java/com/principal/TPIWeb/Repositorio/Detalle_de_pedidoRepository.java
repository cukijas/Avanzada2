package com.principal.TPIWeb.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.principal.TPIWeb.Modelo.Detalle_de_pedido;

@Repository
public interface  Detalle_de_pedidoRepository extends JpaRepository<Detalle_de_pedido, Integer> {

}

package com.principal.TPIWeb.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.principal.TPIWeb.Modelo.Pedido;

@Repository
public interface  PedidoRepository extends JpaRepository <Pedido, Integer> {

}
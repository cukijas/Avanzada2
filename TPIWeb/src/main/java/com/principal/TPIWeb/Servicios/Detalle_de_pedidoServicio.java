package com.principal.TPIWeb.Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.principal.TPIWeb.Modelo.Detalle_de_pedido;
import com.principal.TPIWeb.Repositorio.Detalle_de_pedidoRepository;

@Service
public class Detalle_de_pedidoServicio {

    @Autowired
    private Detalle_de_pedidoRepository repositorio;
    
    public List<Detalle_de_pedido> listarDetalle_de_pedidos() {
        return repositorio.findAll();
    }
    
    public Detalle_de_pedido obtenerDetalle_de_pedidoPorId(int id) {
        return repositorio.findById(id).get();
    }

    public Detalle_de_pedido agregarDetalle_de_pedido(Detalle_de_pedido detalle_de_pedido) {
        return repositorio.save(detalle_de_pedido);
    }
    

}

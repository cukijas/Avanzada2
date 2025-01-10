package com.principal.TPIWeb.Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.principal.TPIWeb.Modelo.Pedido;
import com.principal.TPIWeb.Repositorio.PedidoRepository;

@Service
public class PedidoServicio {

    @Autowired
    private PedidoRepository repositorio;

    public List<Pedido> listarPedidos()
    {
        return repositorio.findAll();
    }

    public Pedido agregarPedido(Pedido pedido) {
        return repositorio.save(pedido);
    }

    public Pedido modificarPedido(Pedido pedido) {
        // Verificar si el artículo existe
        Pedido existente = repositorio.findById(pedido.getId())
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
       
                // Actualizar los datos necesarios
        existente.setEstado(pedido.getEstado());

        // Guardar cambios
        return repositorio.save(existente);
    }

    public Pedido obtenerPedidoPorId(int id) {
        return repositorio.findById(id).get();
    }
}   

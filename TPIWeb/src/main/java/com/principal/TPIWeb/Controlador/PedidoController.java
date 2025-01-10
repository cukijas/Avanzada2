package com.principal.TPIWeb.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.principal.TPIWeb.Modelo.Pedido;
import com.principal.TPIWeb.Servicios.PedidoServicio;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoServicio servicio;

    @GetMapping
    public List<Pedido> listarPedidos() {
        return servicio.listarPedidos();
    }

    @GetMapping("/{id}")
    public Pedido obtenerPedidoPorId(@PathVariable int id) {
        return servicio.obtenerPedidoPorId(id);
    }

    @PostMapping
    public ResponseEntity<Pedido> agregarPedido(@RequestBody Pedido pedido) {
        try {
            // Guardar el artículo en la base de datos
            Pedido PedidoGuardado = servicio.agregarPedido(pedido);

            // Devolver el artículo guardado con estado 201 (Created)
            return ResponseEntity.status(HttpStatus.CREATED).body(PedidoGuardado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Pedido> modificarPedido(@PathVariable int id, @RequestBody Pedido pedido) {
        try {
            // Guardar el artículo en la base de datos
            Pedido PedidoGuardado = servicio.modificarPedido(pedido);

            // Devolver el artículo guardado con estado 201 (Created)
            return ResponseEntity.status(HttpStatus.CREATED).body(PedidoGuardado);
        } catch (Exception e) {
            // Manejar errores
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}

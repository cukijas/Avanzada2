package com.principal.TPIWeb.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.principal.TPIWeb.Modelo.Detalle_de_pedido;
import com.principal.TPIWeb.Servicios.Detalle_de_pedidoServicio;

@RestController
@RequestMapping("/api/detalle_de_pedidos")
public class Detalle_de_pedidoController {
    @Autowired
    private Detalle_de_pedidoServicio servicio;

    @GetMapping
    public List<Detalle_de_pedido> listarDetalle_de_pedidos() {
        return servicio.listarDetalle_de_pedidos();
    }

    @GetMapping("/{id}")
    public Detalle_de_pedido obtenerDetalle_de_pedidoPorId(@PathVariable int id) {
        return servicio.obtenerDetalle_de_pedidoPorId(id);
    }
    
    @PostMapping
    public ResponseEntity<Detalle_de_pedido> agregarDetalle_de_pedido(@RequestBody Detalle_de_pedido detalle_de_pedido) {
        try {
            // Guardar el artículo en la base de datos
            Detalle_de_pedido Detalle_de_pedidoGuardado = servicio.agregarDetalle_de_pedido(detalle_de_pedido);

            // Devolver el artículo guardado con estado 201 (Created)
            return ResponseEntity.status(HttpStatus.CREATED).body(Detalle_de_pedidoGuardado);
        } catch (Exception e) {
            // Manejar errores
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

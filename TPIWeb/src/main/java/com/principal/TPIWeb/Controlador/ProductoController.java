package com.principal.TPIWeb.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.principal.TPIWeb.Modelo.Producto;
import com.principal.TPIWeb.Servicios.ProductoServicio;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoServicio servicio;

    @GetMapping
    public List<Producto> listarProductos() {
        return servicio.listarProductos();
    }
}

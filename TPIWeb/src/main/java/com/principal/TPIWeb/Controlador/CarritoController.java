package com.principal.TPIWeb.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.principal.TPIWeb.Modelo.Carrito;
import com.principal.TPIWeb.Servicios.CarritoServicio;

@RestController
@RequestMapping("/api/carritos")
public class CarritoController {
    @Autowired
    private CarritoServicio servicio;

    @GetMapping
    public List<Carrito> listarCarritos() {
        return servicio.listarCarritos();
    }
    
}

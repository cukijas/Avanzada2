package com.principal.TPIWeb.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.principal.TPIWeb.Modelo.Persona;
import com.principal.TPIWeb.Servicios.UsuarioServicio;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioControlller {

    @Autowired
    private UsuarioServicio servicio;

    @GetMapping
    public List<Persona> listarUsuarios() {
        return servicio.listarUsuarios();
    }
}

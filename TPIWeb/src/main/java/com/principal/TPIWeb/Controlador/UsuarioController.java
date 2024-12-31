package com.principal.TPIWeb.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.principal.TPIWeb.Modelo.Persona;
import com.principal.TPIWeb.Servicios.UsuarioServicio;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioServicio servicio;

    @GetMapping
    public List<Persona> listarUsuarios() {
        return servicio.listarUsuarios();
    }
    @PostMapping
    public ResponseEntity<Persona> agregarUsuario(@RequestBody Persona usuario) {
        try {
            // Guardar el artículo en la base de datos
            Persona UsuarioGuardado = servicio.agregarUsuario(usuario);

            // Devolver el artículo guardado con estado 201 (Created)
            return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioGuardado);
        } catch (Exception e) {
            // Manejar errores
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

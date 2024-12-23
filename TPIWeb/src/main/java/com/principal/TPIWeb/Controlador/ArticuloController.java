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

import com.principal.TPIWeb.Modelo.Articulo;
import com.principal.TPIWeb.Servicios.ArticuloServicio;

@RestController
@RequestMapping("/api/articulos")
public class ArticuloController {

    @Autowired
    private ArticuloServicio servicio;

    @GetMapping
    public List<Articulo> listarArticulos() {
        return servicio.listarArticulos();
    }

    @GetMapping("/{id}")
    public Articulo obtenerArticuloPorId(int id) {
        return servicio.obtenerArticuloPorId(id);
    }

    @PostMapping
    public ResponseEntity<Articulo> agregarArticulo(@RequestBody Articulo articulo) {
        try {
            // Guardar el artículo en la base de datos
            Articulo articuloGuardado = servicio.escribirArticulo(articulo);

            // Devolver el artículo guardado con estado 201 (Created)
            return ResponseEntity.status(HttpStatus.CREATED).body(articuloGuardado);
        } catch (Exception e) {
            // Manejar errores
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

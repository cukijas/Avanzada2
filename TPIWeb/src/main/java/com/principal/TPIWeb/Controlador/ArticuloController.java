package com.principal.TPIWeb.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    public void agregarArticulo(Articulo articulo) {
        servicio.escribirArticulo(articulo);
    }

    public void modificarArticulo(Articulo articulo) {
        servicio.modificarArticulo(articulo);
    }

    public void eliminarArticulo(int id) {
        servicio.eliminarArticulo(id);
    }

}

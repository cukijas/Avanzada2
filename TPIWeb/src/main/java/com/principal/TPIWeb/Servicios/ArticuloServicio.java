package com.principal.TPIWeb.Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.principal.TPIWeb.Modelo.Articulo;
import com.principal.TPIWeb.Repositorio.ArticuloRepository;

@Service
public class ArticuloServicio {

    @Autowired
    private ArticuloRepository repositorio;

    public List<Articulo> listarArticulos() {
        return repositorio.findAll();
    }

    public Articulo escribirArticulo(Articulo articulo) {
        return repositorio.save(articulo);
    }

    public Articulo obtenerArticuloPorId(int id) {
        return repositorio.findById(id).orElse(null);
    }

    public Articulo modificarArticulo(Articulo articulo) {
        // Verificar si el artículo existe
        Articulo existente = repositorio.findById(articulo.getId_articulo())
                .orElseThrow(() -> new RuntimeException("Artículo no encontrado"));
        // Actualizar los datos necesarios
        existente.setCantidad_Articulo(articulo.getCantidad_Articulo());

        // Guardar cambios
        return repositorio.save(existente);
    }

    public void eliminarArticulo(int id) {
        repositorio.deleteById(id);
    }
}

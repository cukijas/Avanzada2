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

    public void modificarArticulo(Articulo articulo) {
        repositorio.save(articulo);
    }

    public void eliminarArticulo(int id) {
        repositorio.deleteById(id);
    }
}

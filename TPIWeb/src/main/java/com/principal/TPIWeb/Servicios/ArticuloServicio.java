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

    public void escribirArticulo(Articulo articulo) {
        repositorio.save(articulo);
    }

    public void obtenerArticuloPorId(int id) {
        repositorio.findById(id).get();
    }

    public void modificarArticulo(Articulo articulo) {
        repositorio.save(articulo);
    }

    public void eliminarArticulo(int id) {
        repositorio.deleteById(id);
    }
}

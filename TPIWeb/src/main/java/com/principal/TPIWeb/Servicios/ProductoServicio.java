package com.principal.TPIWeb.Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.principal.TPIWeb.Modelo.Producto;
import com.principal.TPIWeb.Repositorio.ProductoRepository;

@Service
public class ProductoServicio {

    @Autowired
    private ProductoRepository repositorio;

    public List<Producto> listarProductos() {
        return repositorio.findAll();
    }

    public void escribirProducto(Producto producto) {
        repositorio.save(producto);
    }

    public Producto obtenerProductoPorId(int id) {
        return repositorio.findById(id).get();
    }

    public void eliminarProducto(int id){
        repositorio.deleteById(id);   
    }
}

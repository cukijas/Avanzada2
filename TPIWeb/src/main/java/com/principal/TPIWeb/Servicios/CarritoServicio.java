package com.principal.TPIWeb.Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.principal.TPIWeb.Modelo.Carrito;
import com.principal.TPIWeb.Repositorio.CarritoRepository;

@Service
public class CarritoServicio {
    
    @Autowired
    private CarritoRepository repositorio;
    
    public List<Carrito> listarCarritos() {
        return repositorio.findAll();
    }
    
    public void escribirCarrito(Carrito carrito) {
        repositorio.save(carrito);
    }

    public void obtenerCarritoPorId(int id) {
        repositorio.findById(id).get();
    }
    
    public void modificarCarrito(Carrito carrito) {
        repositorio.save(carrito);
    }
}

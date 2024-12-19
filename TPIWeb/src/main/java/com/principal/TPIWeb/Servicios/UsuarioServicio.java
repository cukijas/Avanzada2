package com.principal.TPIWeb.Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.principal.TPIWeb.Modelo.Persona;
import com.principal.TPIWeb.Repositorio.UsuarioRepository;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepository repositorio;

    public List<Persona> listarUsuarios() {
        return repositorio.findAll();
    }

    public Persona obtenerUsuarioPorId(int id) {
        return repositorio.findById(id).get();
    }

    public void eliminarUsuario(int id) {
        repositorio.deleteById(id);
    }
}

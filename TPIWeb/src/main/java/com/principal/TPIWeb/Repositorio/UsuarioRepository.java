package com.principal.TPIWeb.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.principal.TPIWeb.Modelo.Persona;

public interface UsuarioRepository extends JpaRepository<Persona, Integer> {

}

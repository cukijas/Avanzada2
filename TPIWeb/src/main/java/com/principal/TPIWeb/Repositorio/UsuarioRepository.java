package com.principal.TPIWeb.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.principal.TPIWeb.Modelo.Persona;

@Repository
public interface UsuarioRepository extends JpaRepository<Persona, Integer> {

}

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

import com.principal.TPIWeb.Modelo.Carrito;
import com.principal.TPIWeb.Servicios.CarritoServicio;

@RestController
@RequestMapping("/api/carritos")
public class CarritoController {
    @Autowired
    private CarritoServicio servicio;

    @GetMapping
    public List<Carrito> listarCarritos() {
        return servicio.listarCarritos();
    }
    
    @PostMapping
    public ResponseEntity<Carrito> agregarCarrito(@RequestBody Carrito carrito) {
        try {
            // Guardar el artículo en la base de datos
            Carrito NuevoCarrito = servicio.agregarCarrito(carrito);

            // Devolver el artículo guardado con estado 201 (Created)
            return ResponseEntity.status(HttpStatus.CREATED).body(NuevoCarrito);
        } catch (Exception e) {
            // Manejar errores
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

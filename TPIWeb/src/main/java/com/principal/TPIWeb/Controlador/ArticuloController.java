package com.principal.TPIWeb.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/{id}")
    public Articulo obtenerArticuloPorId(@PathVariable(required = true) Integer id) {
        return servicio.obtenerArticuloPorId(id);
    }

    @PostMapping
    public ResponseEntity<Articulo> agregarArticulo(@RequestBody Articulo articulo) {
        try {
            // Guardar el artículo en la base de datos
            Articulo articuloGuardado = servicio.escribirArticulo(articulo);

            // Devolver el artículo guardado con estado 201 (Created)
            return ResponseEntity.status(HttpStatus.CREATED).body(articuloGuardado);
        } catch (Exception e) {
            // Manejar errores
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Articulo> actualizarArticulo(@PathVariable int id, @RequestBody Articulo articulo) {
        try {
            if(id <= 0)
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            // Me aseguro de que el artículo a actualizar tenga el ID correcto
            articulo.setId_articulo(id);
    
            // Actualizar el artículo en la base de datos
            Articulo articuloActualizado = servicio.modificarArticulo(articulo);
            
            if (articuloActualizado == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    
            // Devolver el artículo actualizado con estado 200 (OK)
            return ResponseEntity.status(HttpStatus.OK).body(articuloActualizado);
        } catch (Exception e) {
            // Manejar errores
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarArticulo(@PathVariable int id) {
        try {
            if(id <= 0)
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            // Eliminar el artículo de la base de datos
            servicio.eliminarArticulo(id);
    
            // Devolver estado 204 (No Content)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            // Manejar errores
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

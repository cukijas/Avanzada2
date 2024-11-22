package Persistencia;

import Modelo.Persona;
import Modelo.Producto;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladoraPersistencia {

    ProductoJpaController ProdJPA = new ProductoJpaController();
    PersonaJpaController UsrJPA = new PersonaJpaController();
    PedidoJpaController PedJPA = new PedidoJpaController();

    //leer los productos en la base de datos
    public List<Producto> LeerProductos() {
        return ProdJPA.findProductoEntities();
    }

    //Administrar Productos
    public void EscribirProducto(Producto prod) {
        ProdJPA.create(prod);

    }

    /*
    public void ActualizarProducto(Producto prod) {
        ProdJPA.create(prod);
    }*/
    public void EliminarProducto(int CodigoProducto) {
        try {
            ProdJPA.destroy(CodigoProducto);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Administrar Usuarios
    /*
    public void EscribirUsuario() {
        //UsrJPA.create();
    }
    
     public void EscribirUsuario() {
        //UsrJPA.create();
    }
     */
    public List<Persona> LeerUsuarios() {
        return UsrJPA.findPersonaEntities();
    }

    public void EscribirUsuario(Persona Usr) {
        UsrJPA.create(Usr);
    }

    public void EliminarUsuario(int id) {
        try {
            UsrJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Producto BuscarProducto(int CodigoProducto) {
        return ProdJPA.findProducto(CodigoProducto);
    }

    public void EditarProducto(Producto prod) {
        try {
            ProdJPA.edit(prod);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Persona BuscarUsuario(int id) {
        return UsrJPA.findPersona(id);
    }

    public void EditarUsuario(Persona Usr) {
        try {
            UsrJPA.edit(Usr);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

package Persistencia;

import Modelo.Producto;
import Persistencia.exceptions.NonexistentEntityException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
    public void EliminarProducto(int CodigoProducto){
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

    
}

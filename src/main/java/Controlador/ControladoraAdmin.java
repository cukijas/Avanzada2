/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Pedido;
import Modelo.Persona;
import Modelo.Producto;
import Persistencia.ControladoraPersistencia;
import Vista.VListarPedidos;
import Vista.VListarProductos;
import Vista.VListarUsuarios;
import java.util.List;

/**
 *
 * @author emito
 */
public class ControladoraAdmin {

    ControladoraPersistencia CtrlPer = new ControladoraPersistencia();
    Producto prod = new Producto();
    Persona Usr = new Persona();
    
    public void AbrirVProductos() {
        VListarProductos ventana = new VListarProductos();
        ventana.setVisible(true);
    }

    public List<Producto> LeerProductos() {
        return CtrlPer.LeerProductos();
    }

    public void EscribirProducto(String Nombre, String Descripcion, float Precio,
            int Stock, String Categoria) {
        prod.setNombre_producto(Nombre);
        prod.setDescripcion(Descripcion);
        prod.setPrecio(Precio);
        prod.setStock(Stock);
        prod.setCategoria(Categoria);
        CtrlPer.EscribirProducto(prod);

    }

    public void AbrirVPedidos() {
        VListarPedidos ventanaP = new  VListarPedidos();
        ventanaP.setVisible(true);
    }

    public void AbrirVUsuarios() {
        VListarUsuarios ventanaU = new VListarUsuarios();
        ventanaU.setVisible(true);
    }

    public void EliminarProducto(int CodigoProducto) {
        CtrlPer.EliminarProducto(CodigoProducto);
    }

    public List<Pedido> LeerPedidos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Persona> LeerUsuarios() {
        return CtrlPer.LeerUsuarios();
    }

    public void EscribirUsuario(String Nombre, String Direccion, String Correo, String Contra) {
        Usr.setNombre(Nombre);
        Usr.setDireccion(Direccion);
        Usr.setCorreo(Correo);
        Usr.setContra(Contra);
        CtrlPer.EscribirUsuario(Usr);
    }

}

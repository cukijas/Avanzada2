/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Producto;
import Persistencia.ControladoraPersistencia;
import Vista.VListarProductos;
import java.util.List;

/**
 *
 * @author emito
 */
public class ControladoraAdmin {

    ControladoraPersistencia CtrlPer = new ControladoraPersistencia();
    Producto prod = new Producto();

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
        //VListarPedidos ventana = new VListarProductos();
        //ventana.setVisible(true);
    }

    public void AbrirVUsuarios() {
        //VListarPedidos ventana = new VListarProductos();
        //ventana.setVisible(true);
    }

    public void EliminarProducto(int CodigoProducto) {
        CtrlPer.EliminarProducto(CodigoProducto);
    }

}

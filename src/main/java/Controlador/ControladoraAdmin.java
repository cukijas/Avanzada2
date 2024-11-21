/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vista.VListarPedidos;
import Vista.VListarProductos;
import Vista.VListarUsuarios;


// controladora logica
public class ControladoraAdmin {

    //metodos intermediarios entre el modelo y las vistas
    public void AbrirVProductos() {
        VListarProductos ventana = new VListarProductos();
        ventana.setVisible(true);
    }

    public void AbrirVPedidos() {
        VListarPedidos ventanaP = new VListarPedidos();
        ventanaP.setVisible(true);
    }

    public void AbrirVUsuarios() {
        VListarUsuarios ventanaU = new VListarUsuarios();
        ventanaU.setVisible(true);
    }

}

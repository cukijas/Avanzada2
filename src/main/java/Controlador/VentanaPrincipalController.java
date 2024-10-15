/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Producto;
import Modelo.Usuario;
import Vista.VentanaLogin;
import Vista.VentanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author emito
 */
public class VentanaPrincipalController implements ActionListener {

    VentanaPrincipal vista;
    Producto prod = new Producto();

    public VentanaPrincipalController(VentanaPrincipal vista) {
        this.vista = vista;
        vista.setVisible(true);
        prod.ListarProductosUsuarios(vista);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        prod.ListarProductosUsuarios(vista);
    }

}

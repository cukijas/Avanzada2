/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Usuario;
import Principal.Conexion;
import Vista.VentanaLogin;
import Vista.VentanaRegister;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author Max
 */
public class RegisterController implements ActionListener {

    Usuario user = new Usuario();
    VentanaRegister vista = new VentanaRegister();

    public RegisterController(VentanaRegister vista, Usuario user) {
        this.vista = vista;
        this.user = user;
        // Registrar el controlador como ActionListener en la vista
        this.vista.btnregister.addActionListener(this);
        vista.setVisible(true);
        vista.setLocationRelativeTo(null);
        this.vista.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int band;
        user.setNombre(vista.txtnombre.getText());
        user.setCorreo(vista.txtmail.getText());
        user.setDireccion(vista.txtdir.getText());
        user.setContra(vista.txtpass.getText());
        band = user.RegistrarUsuario();
        if (band == 1) {
            vista.dispose();
            JOptionPane.showMessageDialog(null, "Usuario Registrado!\n Ya puede Iniciar sesion");
            VentanaLogin login = new VentanaLogin();
            LoginController ctrl = new LoginController(login, user);
        } else {
            if (band == 0) {
                vista.limpiar();

            }

        }

    }
}

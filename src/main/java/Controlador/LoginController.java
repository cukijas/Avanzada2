/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Usuario;
import Vista.VentanaLogin;
import Vista.VentanaRegister;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author emito
 */
/*public class LoginController implements ActionListener{
    
    Usuario user = new Usuario();
    VentanaLogin vista = new VentanaLogin();    
   
    public LoginController(VentanaLogin vista, Usuario user) {
        this.vista = vista;
        this.user = user;
        // Registrar el controlador como ActionListener en la vista
        this.vista.btnlogin.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) 
    {
        int band = 0;
        user.setCorreo(vista.txtmail.getText());
        user.setContra(vista.txtpass.getText());
        band = user.RegistrarUsuario();
        if(band == 1){
            vista.dispose();
            JOptionPane.showMessageDialog(null, "Usuario Registrado!\n Ya puede Iniciar sesion");
        }
        
    }
}*/

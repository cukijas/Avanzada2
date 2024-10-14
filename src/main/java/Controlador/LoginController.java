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
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author emito
 */
public class LoginController implements ActionListener{
    
    Usuario user = new Usuario();
    VentanaLogin vista = new VentanaLogin();    
   
    public LoginController(VentanaLogin vista, Usuario user) {
        this.vista = vista;
        this.user = user;
        // Registrar el controlador como ActionListener en la vista
        this.vista.btnlogin.addActionListener(this);
       // this.vista.labelregister.addKeyListener();
    }
    
    public void actionPerformed(ActionEvent e) 
    {
        boolean band;
        user.setCorreo(vista.txtmail.getText());
        user.setContra(vista.txtpass.getText());
        band = user.IniciarSesion();
        if(band){
            vista.dispose();
            JOptionPane.showMessageDialog(null, "Felicidades!");
        }
        
    }
    public void mouseClicked(MouseEvent e){
        System.out.println("hola");
            
    }
}

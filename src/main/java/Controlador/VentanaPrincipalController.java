/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

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

    VentanaPrincipal vista = new VentanaPrincipal();
    
    
    public VentanaPrincipalController (VentanaPrincipal vista) {
        this.vista = vista;
        // Registrar el controlador como ActionListener en la vista
        //this.vista.btnlogin.addActionListener(this);
       // this.vista.labelregister.addKeyListener();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       
    }
    
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Usuario;
import Vista.VentanaAdministrador;
import Vista.VentanaLogin;
import Vista.VentanaPrincipal;
import Vista.VentanaRegister;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author emito
 */
public class LoginController implements ActionListener, MouseListener {

    Usuario user;
    VentanaLogin vista;

    public LoginController(VentanaLogin vista, Usuario user) {
        this.vista = vista;
        this.user = user;
        // Registrar el controlador como ActionListener en la vista
        this.vista.btnlogin.addActionListener(this);
        this.vista.labelregister.addMouseListener(this);
        this.vista.setVisible(true); // Descomenta esto si necesitas mostrar la ventana
        this.vista.setResizable(false);
        this.vista.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        boolean band;
        user.setCorreo(vista.txtmail.getText());
        user.setContra(vista.txtpass.getText());
        try {
            band = user.IniciarSesion();
            if (band) {
                //Cerrar ventana de Inicio de sesion
                vista.dispose();
                if (vista.btnadmin.isSelected()) {
                   VentanaAdministrador VistaMainAdm = new VentanaAdministrador();
                   VentanaAdministradorCtrl ControladorAdm = new VentanaAdministradorCtrl(VistaMainAdm);
                } else {
                    //Inicio sesion correctamente, se despliega la pantalla principal
                    VentanaPrincipal VistaMain = new VentanaPrincipal();
                    VentanaPrincipalController controladorPrincipal = new VentanaPrincipalController(VistaMain);
                    //JOptionPane.showMessageDialog(null, "Felicidades!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Intente Nuevamente", "Datos Incorrectos!", 0); //esta bien asi?
            }
        } catch (Exception o) {
            JOptionPane.showMessageDialog(null, "Intente Nuevamente", "Error!", 0);
        }

    }

    public void mouseClicked(MouseEvent evt) {
        // Cambiar a la vista de registro
        vista.dispose();
        VentanaRegister reg = new VentanaRegister();
        RegisterController ctrlreg = new RegisterController(reg, user); // Asumimos que existe una clase VentanaRegistro

    }

    // Métodos vacíos para los demás eventos del MouseListener
    public void mousePressed(MouseEvent evt) {
    }

    public void mouseReleased(MouseEvent evt) {
    }

    public void mouseEntered(MouseEvent evt) {
    }

    public void mouseExited(MouseEvent evt) {
    }

}

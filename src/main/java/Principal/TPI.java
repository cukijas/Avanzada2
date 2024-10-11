/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Principal;

import Vista.VentanaRegister;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Max 
 */
public class TPI {

    public static void main(String[] args) {
        Connection con = basededatos.conectar();
        if (con != null) {
            try {
                // Cerrar la conexión al terminar
                con.close();
                System.out.println("Conexión cerrada");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        VentanaRegister ventana1 = new VentanaRegister();
        ventana1.setVisible(true);
    }
}

import Controlador.LoginController;
import Controlador.RegisterController;
import Modelo.Usuario;
import Vista.VentanaLogin;
import Vista.VentanaRegister;
import java.sql.Connection;
import java.sql.SQLException;

public class TPI {
    public static void main(String[] args) {
        
        //VentanaRegister ventana1 = new VentanaRegister();
        VentanaLogin ventana1 = new VentanaLogin();
        Usuario User = new Usuario();
        // Descomenta esto si necesitas abrir la ventana
        ventana1.setVisible(true); // Descomenta esto si necesitas mostrar la ventana
        ventana1.setLocationRelativeTo(null);
        LoginController ctrl = new LoginController(ventana1, User);
        //RegisterController ctrl = new RegisterController(ventana1, User);
        
    }
}

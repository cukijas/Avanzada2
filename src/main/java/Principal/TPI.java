import Controlador.RegisterController;
import Modelo.Usuario;
import Vista.VentanaRegister;
import java.sql.Connection;
import java.sql.SQLException;

public class TPI {
    public static void main(String[] args) {
        VentanaRegister ventana1 = new VentanaRegister();
        Usuario User = new Usuario();
        // Descomenta esto si necesitas abrir la ventana
        ventana1.setVisible(true); // Descomenta esto si necesitas mostrar la ventana
        ventana1.setLocationRelativeTo(null);
        RegisterController ctrl = new RegisterController(ventana1, User);
        
    }
}

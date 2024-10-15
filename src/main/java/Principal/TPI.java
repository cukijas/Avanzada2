import Controlador.LoginController;
import Controlador.RegisterController;
import Controlador.VentanaPrincipalController;
import Modelo.Usuario;
import Vista.VListarProductos;
import Vista.VentanaLogin;
import Vista.VentanaPrincipal;
import Vista.VentanaRegister;
import java.sql.Connection;
import java.sql.SQLException;

public class TPI {
    public static void main(String[] args) {
        
        /*VentanaRegister reg = new VentanaRegister();
        reg.setVisible(true);*/
        VentanaLogin ventana1 = new VentanaLogin();
        Usuario User = new Usuario();
        // Descomenta esto si necesitas abrir la ventana
        LoginController ctrl = new LoginController(ventana1, User);
        
        VentanaPrincipal v = new VentanaPrincipal();
        //VentanaPrincipalController ctrlp = new VentanaPrincipalController(v);
        //VListarProductos listarpedidos = new VListarProductos();
        //listarpedidos.setVisible(true);
        
    }
}

import Vista.VentanaRegister;
import java.sql.Connection;
import java.sql.SQLException;

public class TPI {
    public static void main(String[] args) {
        
        //Connection con = Conexion.conectar(); // Llama al método de conexión

        /*if (conexion != null) {
            try {
                // Aquí puedes realizar operaciones con la base de datos
                // Cerrar la conexión al terminar
                conexion.close();
                System.out.println("Conexión cerrada");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }*/
        
        
        VentanaRegister ventana1 = new VentanaRegister(); // Descomenta esto si necesitas abrir la ventana
        ventana1.setVisible(true); // Descomenta esto si necesitas mostrar la ventana
        
    }
}

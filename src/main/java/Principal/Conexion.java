import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/TPIPA"; // Cambia 'TPIPA' por tu nombre de base de datos
    private static final String USER = "root"; // Cambia 'root' si es necesario
    private static final String PASS = ""; // Cambia la contraseña si es necesario

    public static Connection conectar() {
        Connection conexion = null;
        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establecer la conexión
            conexion = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexión exitosa a la base de datos");

        } catch (SQLException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Driver no encontrado: " + e.getMessage());
        }
        return conexion;
    }
}

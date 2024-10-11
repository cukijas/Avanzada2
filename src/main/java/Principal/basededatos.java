import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class basededatos {
    // Atributos para la conexión
    private static final String URL = "jdbc:mysql://localhost:3306/TPIPA"; // Cambia el nombre de la base de datos si es necesario
    private static final String USER = "root"; // Usuario por defecto
    private static final String PASSWORD = ""; // Contraseña por defecto

    // Método para conectarse a la base de datos
    public static Connection conectar() {
        Connection bd = null;
        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establecer la conexión
            bd = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos");

        } catch (SQLException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Driver no encontrado: " + e.getMessage());
        }
        return bd;
    }
}
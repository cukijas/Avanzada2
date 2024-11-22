package Principal;

import Vista.VentanaAdministrador;

/* Este proyecto sigue el patron de diseño MVC y Utiliza JPA 
para persistir los datos en una base de datos MySQL
Utilizando PHPMyAdmin con XAMP
Nombre de la base de datos: tpipa
Integrantes del grupo:
-Romero, Micaela Denisse
-Toledo,Alejandro Emiliano*/
public class TPI {

    public static void main(String[] args) {
        //abre la ventana principal
        VentanaAdministrador admin = new VentanaAdministrador();
        admin.setVisible(true);
        admin.setLocationRelativeTo(null);
    }
}

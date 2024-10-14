/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Principal.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Max
 */
public class Persona {

    private String Nombre;
    private String Direccion;
    private String Correo;
    private String Contra;

    /**
     * @return the Nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * @param Nombre the Nombre to set
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    /**
     * @return the Direccion
     */
    public String getDireccion() {
        return Direccion;
    }

    /**
     * @param Direccion the Direccion to set
     */
    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    /**
     * @return the Correo
     */
    public String getCorreo() {
        return Correo;
    }

    /**
     * @param Correo the Correo to set
     */
    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    /**
     * @return the Contra
     */
    public String getContra() {
        return Contra;
    }

    /**
     * @param Contra the Contra to set
     */
    public void setContra(String Contra) {
        this.Contra = Contra;
    }
    
    //CASO DE USO: Iniciar Sesion
    public boolean IniciarSesion() {
        Conexion con = new Conexion();
        Connection cn = Conexion.conectar();
        String consulta = "SELECT * FROM usuarios WHERE Correo LIKE ? AND Contraseña LIKE ?;";
        try {
            PreparedStatement ps = cn.prepareStatement(consulta);
            ps.setString(1, Correo);
            ps.setString(2, Contra);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                 return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Error al registrar el usuario: " + ex);
        }
        return false;
    }
    
    //CASO DE USO: Registrar Usuario
    public boolean RegistrarUsuario() {
        Conexion con = new Conexion();
        Connection cn = Conexion.conectar();
        try {
            String consulta = "INSERT INTO `usuarios`( `Nombre`, `Direccion`, `Correo`, `Contraseña`) "
                    + "VALUES (?, ?, ?, ?)";

            PreparedStatement ps = cn.prepareStatement(consulta);
            ps.setString(1, Nombre);
            ps.setString(2, Direccion);
            ps.setString(3, Correo);
            ps.setString(4, Contra);

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al registrar el usuario: " + ex);
        }
        return false;
    }
}

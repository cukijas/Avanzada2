/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Principal.Conexion;
import Vista.VentanaPrincipal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Max
 */
public class Producto {

    private int Codigo_Producto;
    private int Nombre_Producto;
    private String descripcion;
    private char categoria;
    private float Precio;
    private int Stock;

    public void ListarProductosUsuarios(VentanaPrincipal vista) {
        Conexion con = new Conexion();
        Connection cn = Conexion.conectar();
        String consulta = "SELECT Nombre, Descripcion, c.Categoria, Precio, PrecioEnvio, Stock "
                + "FROM productos p INNER JOIN categorias c "
                + "ON c.ID_categoria = p.Categoria "; //consulta para tomar las categorias
        String N, D, C, P, S, PE;
        try {
            PreparedStatement ps = cn.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = new DefaultTableModel(new String[]{"Nombre", "Descripcion", 
                "Categoria", "Precio","Precio de Envio", "Stock"}, 0);
            vista.listaproductos.setModel(model);
            while (rs.next()) {
                N = rs.getString("Nombre");
                D = rs.getString("Descripcion");
                C = rs.getString("Categoria");
                P = rs.getString("Precio");
                PE = rs.getString("PrecioEnvio");
                S = rs.getString("Stock");
                model.addRow(new Object[]{N, D, C, P, PE, S});
            }
        } catch (SQLException error) {
            System.out.println("Error al cargar productos" + error);
        }
    }

    /**
     * @return the Codigo_Producto
     */
    public int getCodigo_Producto() {
        return Codigo_Producto;
    }

    /**
     * @param Codigo_Producto the Codigo_Producto to set
     */
    public void setCodigo_Producto(int Codigo_Producto) {
        this.Codigo_Producto = Codigo_Producto;
    }

    /**
     * @return the Nombre_Producto
     */
    public int getNombre_Producto() {
        return Nombre_Producto;
    }

    /**
     * @param Nombre_Producto the Nombre_Producto to set
     */
    public void setNombre_Producto(int Nombre_Producto) {
        this.Nombre_Producto = Nombre_Producto;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the categoria
     */
    public char getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(char categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the Precio
     */
    public float getPrecio() {
        return Precio;
    }

    /**
     * @param Precio the Precio to set
     */
    public void setPrecio(float Precio) {
        this.Precio = Precio;
    }

    /**
     * @return the Stock
     */
    public int getStock() {
        return Stock;
    }

    /**
     * @param Stock the Stock to set
     */
    public void setStock(int Stock) {
        this.Stock = Stock;
    }

}

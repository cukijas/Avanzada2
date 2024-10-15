/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.VentanaPrincipal;

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
    
    public void ListarProductosUsuarios(VentanaPrincipal vista)
    {
        
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

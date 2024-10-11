/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Max 
 */
public class Pedido 
{
    private int Codigo_Pedido;
    private char Estado_Pedido;
    private int Fecha_Pedido;
    private float Total_pedido;

    /**
     * @return the Codigo_Pedido
     */
    public int getCodigo_Pedido() {
        return Codigo_Pedido;
    }

    /**
     * @param Codigo_Pedido the Codigo_Pedido to set
     */
    public void setCodigo_Pedido(int Codigo_Pedido) {
        this.Codigo_Pedido = Codigo_Pedido;
    }

    /**
     * @return the Estado_Pedido
     */
    public char getEstado_Pedido() {
        return Estado_Pedido;
    }

    /**
     * @param Estado_Pedido the Estado_Pedido to set
     */
    public void setEstado_Pedido(char Estado_Pedido) {
        this.Estado_Pedido = Estado_Pedido;
    }

    /**
     * @return the Fecha_Pedido
     */
    public int getFecha_Pedido() {
        return Fecha_Pedido;
    }

    /**
     * @param Fecha_Pedido the Fecha_Pedido to set
     */
    public void setFecha_Pedido(int Fecha_Pedido) {
        this.Fecha_Pedido = Fecha_Pedido;
    }

    /**
     * @return the Total_pedido
     */
    public float getTotal_pedido() {
        return Total_pedido;
    }

    /**
     * @param Total_pedido the Total_pedido to set
     */
    public void setTotal_pedido(float Total_pedido) {
        this.Total_pedido = Total_pedido;
    }
   
    
}

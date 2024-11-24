/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author emito
 */
public class Detalle_de_pedido {

    private int ID_de_detalle;
    private float Precio_de_envio;
    private int Cantidad_de_Articulo;
    private float Total_por_Articulo;

    public Detalle_de_pedido() {
    }

    public Detalle_de_pedido(int ID_de_detalle, float Precio_de_envio, int Cantidad_de_Articulo, float Total_por_Articulo) {
        this.ID_de_detalle = ID_de_detalle;
        this.Precio_de_envio = Precio_de_envio;
        this.Cantidad_de_Articulo = Cantidad_de_Articulo;
        this.Total_por_Articulo = Total_por_Articulo;
    }

    public int getID_de_detalle() {
        return ID_de_detalle;
    }

    public void setID_de_detalle(int ID_de_detalle) {
        this.ID_de_detalle = ID_de_detalle;
    }

    public float getPrecio_de_envio() {
        return Precio_de_envio;
    }

    public void setPrecio_de_envio(float Precio_de_envio) {
        this.Precio_de_envio = Precio_de_envio;
    }

    public int getCantidad_de_Articulo() {
        return Cantidad_de_Articulo;
    }

    public void setCantidad_de_Articulo(int Cantidad_de_Articulo) {
        this.Cantidad_de_Articulo = Cantidad_de_Articulo;
    }

    public float getTotal_por_Articulo() {
        return Total_por_Articulo;
    }

    public void setTotal_por_Articulo(float Total_por_Articulo) {
        this.Total_por_Articulo = Total_por_Articulo;
    }

}

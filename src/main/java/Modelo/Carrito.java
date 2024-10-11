/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Max 
 */
public class Carrito {
    
    private int Cantidad_Producto;
    private float Detalle_envio;
    private float PrecioXarticulo;
    private float resumenCarrito;

    /**
     * @return the Cantidad_Producto
     */
    public int getCantidad_Producto() {
        return Cantidad_Producto;
    }

    /**
     * @param Cantidad_Producto the Cantidad_Producto to set
     */
    public void setCantidad_Producto(int Cantidad_Producto) {
        this.Cantidad_Producto = Cantidad_Producto;
    }

    /**
     * @return the Detalle_envio
     */
    public float getDetalle_envio() {
        return Detalle_envio;
    }

    /**
     * @param Detalle_envio the Detalle_envio to set
     */
    public void setDetalle_envio(float Detalle_envio) {
        this.Detalle_envio = Detalle_envio;
    }

    /**
     * @return the PrecioXarticulo
     */
    public float getPrecioXarticulo() {
        return PrecioXarticulo;
    }

    /**
     * @param PrecioXarticulo the PrecioXarticulo to set
     */
    public void setPrecioXarticulo(float PrecioXarticulo) {
        this.PrecioXarticulo = PrecioXarticulo;
    }

    /**
     * @return the resumenCarrito
     */
    public float getResumenCarrito() {
        return resumenCarrito;
    }

    /**
     * @param resumenCarrito the resumenCarrito to set
     */
    public void setResumenCarrito(float resumenCarrito) {
        this.resumenCarrito = resumenCarrito;
    }
    
   
}

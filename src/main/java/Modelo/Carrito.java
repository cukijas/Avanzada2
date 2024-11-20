/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Carrito implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int Cantidad_Producto;
    private float Detalle_envio;
    private float PrecioXarticulo;
    private float resumenCarrito;

    public Carrito() {
    }

    public Carrito(int Cantidad_Producto, float Detalle_envio, float PrecioXarticulo) {
        this.Cantidad_Producto = Cantidad_Producto;
        this.Detalle_envio = Detalle_envio;
        this.PrecioXarticulo = PrecioXarticulo;
    }

    public int getCantidad_Producto() {
        return Cantidad_Producto;
    }

    public void setCantidad_Producto(int Cantidad_Producto) {
        this.Cantidad_Producto = Cantidad_Producto;
    }

    public float getDetalle_envio() {
        return Detalle_envio;
    }

    public void setDetalle_envio(float Detalle_envio) {
        this.Detalle_envio = Detalle_envio;
    }

    public float getPrecioXarticulo() {
        return PrecioXarticulo;
    }

    public void setPrecioXarticulo(float PrecioXarticulo) {
        this.PrecioXarticulo = PrecioXarticulo;
    }

    public float getResumenCarrito() {
        return resumenCarrito;
    }

    public void setResumenCarrito(float resumenCarrito) {
        this.resumenCarrito = resumenCarrito;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.principal.TPIWeb.Modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;



@Entity
public class Detalle_de_pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private float Precio_de_envio;
    private int Cantidad_de_Articulo;
    private float Total_por_Articulo;
    
    @ManyToOne
    private Producto producto;

    @ManyToOne
    private Pedido pedido;

    public Detalle_de_pedido() {
    }

    public Detalle_de_pedido(int Cantidad_de_Articulo, float Precio_de_envio, float Total_por_Articulo, int id, Pedido pedido, Producto producto) {
        this.Cantidad_de_Articulo = Cantidad_de_Articulo;
        this.Precio_de_envio = Precio_de_envio;
        this.Total_por_Articulo = Total_por_Articulo;
        this.id = id;
        this.pedido = pedido;
        this.producto = producto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.principal.TPIWeb.Modelo;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Articulo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_articulo;
    private int Cantidad_Articulo;
    @ManyToOne
    private Producto producto;
    @ManyToOne
    private Carrito carrito;

    public Articulo() {
    }

    public Articulo(int Cantidad_Articulo, Carrito carrito, int id_articulo, Producto producto) {
        this.Cantidad_Articulo = Cantidad_Articulo;
        this.carrito = carrito;
        this.id_articulo = id_articulo;
        this.producto = producto;
    }

    public int getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(int id_articulo) {
        this.id_articulo = id_articulo;
    }

    public int getCantidad_Articulo() {
        return Cantidad_Articulo;
    }

    public void setCantidad_Articulo(int Cantidad_Articulo) {
        this.Cantidad_Articulo = Cantidad_Articulo;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

}

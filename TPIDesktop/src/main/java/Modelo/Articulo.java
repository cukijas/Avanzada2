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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Articulo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_articulo;
    private int Cantidad_Articulo;
    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;
    @ManyToOne
    @JoinColumn(name = "carrito_id", nullable = false)
    private Carrito carrito;

    public Articulo() {
    }

    public Articulo(int Cantidad_Articulo, Carrito carrito, int id_articulo, Producto producto) {
        this.Cantidad_Articulo = Cantidad_Articulo;
        this.carrito = carrito;
        this.id_articulo = id_articulo;
        this.producto = producto;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Articulo{");
        sb.append("id_articulo=").append(id_articulo);
        sb.append(", Cantidad_Articulo=").append(Cantidad_Articulo);
        sb.append(", producto=").append(producto);
        sb.append(", carrito=").append(carrito);
        sb.append('}');
        return sb.toString();
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

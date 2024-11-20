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
public class Pedido implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) 
    private int id;
    private String Estado_Pedido;
    private int Fecha_Pedido;
    private float Total_pedido;

    public Pedido() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado_Pedido() {
        return Estado_Pedido;
    }

    public void setEstado_Pedido(String Estado_Pedido) {
        this.Estado_Pedido = Estado_Pedido;
    }

    public int getFecha_Pedido() {
        return Fecha_Pedido;
    }

    public void setFecha_Pedido(int Fecha_Pedido) {
        this.Fecha_Pedido = Fecha_Pedido;
    }

    public float getTotal_pedido() {
        return Total_pedido;
    }

    public void setTotal_pedido(float Total_pedido) {
        this.Total_pedido = Total_pedido;
    }

    public Pedido(int id, String Estado_Pedido, int Fecha_Pedido, float Total_pedido) {
        this.id = id;
        this.Estado_Pedido = Estado_Pedido;
        this.Fecha_Pedido = Fecha_Pedido;
        this.Total_pedido = Total_pedido;
    }
    
}

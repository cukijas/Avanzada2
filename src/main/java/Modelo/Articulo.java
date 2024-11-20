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
public class Articulo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_articulo;
    private int Cantidad_Articulo;
    private char Nombre_Articulo;

    public Articulo() {
    }

    public Articulo(int id_articulo, int Cantidad_Articulo, char Nombre_Articulo) {
        this.id_articulo = id_articulo;
        this.Cantidad_Articulo = Cantidad_Articulo;
        this.Nombre_Articulo = Nombre_Articulo;
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

    public char getNombre_Articulo() {
        return Nombre_Articulo;
    }

    public void setNombre_Articulo(char Nombre_Articulo) {
        this.Nombre_Articulo = Nombre_Articulo;
    }

}

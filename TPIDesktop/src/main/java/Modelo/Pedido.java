/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) 
    private int id;
    private String Estado;
    private String Fecha;
    private float Total;

    @OneToMany
    private List<Detalle_de_pedido> Detalle;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Persona usuario;

    
    public Pedido() {
    }

    public Pedido(List<Detalle_de_pedido> Detalle, String Estado, String Fecha, float Total, int id, Persona usuario) {
        this.Detalle = Detalle;
        this.Estado = Estado;
        this.Fecha = Fecha;
        this.Total = Total;
        this.id = id;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public float getTotal() {
        return Total;
    }

    public void setTotal(float Total) {
        this.Total = Total;
    }

    public List<Detalle_de_pedido> getDetalle() {
        return Detalle;
    }

    public void setDetalle(List<Detalle_de_pedido> Detalle) {
        this.Detalle = Detalle;
    }

    public Persona getUsuario() {
        return usuario;
    }

    public void setUsuario(Persona usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido{");
        sb.append("id=").append(id);
        sb.append(", Estado=").append(Estado);
        sb.append(", Fecha=").append(Fecha);
        sb.append(", Total=").append(Total);
        sb.append(", Detalle=").append(Detalle);
        sb.append(", usuario=").append(usuario);
        sb.append('}');
        return sb.toString();
    }



}
    
   
    

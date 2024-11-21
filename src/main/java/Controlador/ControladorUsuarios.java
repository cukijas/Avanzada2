/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Persona;
import Persistencia.ControladoraPersistencia;
import java.util.List;

//controladora logica de VListarUsuarios
public class ControladorUsuarios {
    
    //instacias de las clases involucradas
    Persona Usr = new Persona();
    ControladoraPersistencia CtrlPer = new ControladoraPersistencia();
    
    //metodos intermediarios entre el controlador de CRUD y la vista
    public List<Persona> LeerUsuarios() {
        return CtrlPer.LeerUsuarios();
    }

    public void EscribirUsuario(String Nombre, String Direccion, String Correo, String Contra) {
        Usr.setNombre(Nombre);
        Usr.setDireccion(Direccion);
        Usr.setCorreo(Correo);
        Usr.setContra(Contra);
        CtrlPer.EscribirUsuario(Usr);
    }

    public void EliminarUsuario(int id) {
        CtrlPer.EliminarUsuario(id);
    }
}

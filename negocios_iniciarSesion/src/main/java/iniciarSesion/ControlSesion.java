/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iniciarSesion;

import dto.ConductorDTO;
import dto.UsuarioDTO;
import objetosNegocio.UsuarioNegocio;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class ControlSesion {
    
    private static ControlSesion instancia;
    
    private  ControlSesion(){
    }
    
    public static ControlSesion getInstancia(){
        if (instancia == null) {
            instancia = new ControlSesion();
        }
        return instancia;
    }
    
    public boolean validarUsuario(UsuarioDTO usuario) {
        return UsuarioNegocio.obtenerInstancia().validarUsuario(usuario);
    }
    
    public UsuarioDTO obtenerUsuario(){
        return UsuarioNegocio.obtenerInstancia().obtenerUsuario();
    }
    
    public ConductorDTO obtenerConductor(){
        return obtenerUsuario().getConductor();
    }
    
    public void cerrarSesion(){
        UsuarioNegocio.cerrarSesion();
    }
}

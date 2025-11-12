/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iniciarSesion;

import dto.UsuarioDTO;
import objetosNegocio.UsuarioNegocio;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class ControlSesion {
    
    public  ControlSesion(){
    }
    
    public boolean validarUsuario(UsuarioDTO usuario) {
        return UsuarioNegocio.obtenerInstancia().validarUsuario(usuario);
    }
    
    public UsuarioDTO obtenerUsuario(){
        return UsuarioNegocio.obtenerInstancia().obtenerUsuario();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iniciarSesion;

import dto.ConductorDTO;
import dto.UsuarioDTO;
import factory.FabricaBOs;
import factory.IFabricaBOs;
import interfaces.IUsuarioNegocio;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class ControlSesion {
    
    private final IUsuarioNegocio usuarioBO;
    
    public  ControlSesion(){
        IFabricaBOs fabrica = new FabricaBOs();
        this.usuarioBO = fabrica.crearUsuarioNegocio();
    }
    
    public boolean validarUsuario(UsuarioDTO usuario) {
        return usuarioBO.validarUsuario(usuario);
    }
    
    public UsuarioDTO obtenerUsuario(){
        return usuarioBO.obtenerUsuarioActivo();
    }
    
    public ConductorDTO obtenerConductor(){
        return usuarioBO.obtenerUsuarioActivo().getConductor();
    }
    
    public void cerrarSesion(){
        usuarioBO.cerrarSesion();
    }
}

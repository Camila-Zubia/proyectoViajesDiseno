/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dto.UsuarioDTO;

/**
 *
 * @author Camila Zubia 00000244825
 */
public interface IUsuarioNegocio {
    
    public void cerrarSesion();
    
    public UsuarioDTO obtenerUsuarioActivo();

    public boolean validarUsuario(UsuarioDTO usuario);
    
}

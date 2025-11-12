/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package iniciarSesion;

import dto.UsuarioDTO;

/**
 *
 * @author Camila Zubia 00000244825
 */
public interface IIniciarSesion {
    
    /**
     * valida que el usuario proporcionado coincida con un usuario y contraseña valida
     * @param usuario
     * @return 
     */
    public boolean validarUsuario(UsuarioDTO usuario);
    
    /**
     * obtiene el usuario actual
     * @return 
     */
    public UsuarioDTO obtenerUsuario();
}

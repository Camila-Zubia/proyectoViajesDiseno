/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iniciarSesion;

import dto.UsuarioDTO;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class IniciarSesion implements IIniciarSesion{
    
    private final ControlSesion control;

    public IniciarSesion() {
        this.control = new ControlSesion();
    }

    @Override
    public boolean validarUsuario(UsuarioDTO usuario) {
        return control.validarUsuario(usuario);
    }

    @Override
    public UsuarioDTO obtenerUsuario() {
        return control.obtenerUsuario();
    }

}

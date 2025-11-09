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
public class IniciarSesion implements IIniciarSesion{
    
    private UsuarioNegocio usuarioBO;

    public IniciarSesion() {
        this.usuarioBO = new UsuarioNegocio();
    }

    @Override
    public boolean validarUsuario(UsuarioDTO usuario) {
        UsuarioDTO usuarioMock = usuarioBO.obtenerUsuario();
        boolean usuarioValido = usuarioMock.getUsuario().equals(usuario.getUsuario());
        boolean contraseñaValida = usuarioMock.getContraseña().equals(usuario.getContraseña());
        if (contraseñaValida && usuarioValido) {
            ControlSesion.iniciarSesion(usuarioMock);
            return true;
        }
        return false;
    }
    
}

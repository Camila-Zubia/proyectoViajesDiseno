/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import dto.ConductorDTO;
import dto.UsuarioDTO;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class UsuarioNegocio {
    private static UsuarioNegocio instancia = new UsuarioNegocio();
    private static UsuarioDTO usuarioActual;

    private UsuarioNegocio() {
        UsuarioNegocio.usuarioActual = new UsuarioDTO("cperez", "1234");
        UsuarioNegocio.usuarioActual.setConductor(new ConductorDTO("Carlos Pérez"));
        
    }

    public static UsuarioDTO obtenerUsuario() {
        return usuarioActual;
    }

    public static ConductorDTO obtenerConductor() {
        return usuarioActual.getConductor();
    }
    
    public static UsuarioNegocio obtenerInstancia(){
        if (instancia == null) {
            instancia = new UsuarioNegocio();
        }
        return instancia;
    }
    
    public static void iniciarSesion(UsuarioDTO usuario) {
        UsuarioNegocio.usuarioActual = usuario;
    }

    public static void cerrarSesion() {
        usuarioActual = null;
    }

    public static boolean haySesionActiva() {
        return usuarioActual != null;
    }

    public static boolean validarUsuario(UsuarioDTO usuario) {
        UsuarioDTO usuarioMock = usuarioActual;
        boolean usuarioValido = usuarioMock.getUsuario().equals(usuario.getUsuario());
        boolean contraseñaValida = usuarioMock.getContraseña().equals(usuario.getContraseña());
        if (contraseñaValida && usuarioValido) {
            UsuarioNegocio.iniciarSesion(usuarioMock);
            return true;
        }
        return false;
    }
}

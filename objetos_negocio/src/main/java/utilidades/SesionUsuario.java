/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import dto.ConductorDTO;
import dto.UsuarioDTO;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class SesionUsuario {
    
    private static SesionUsuario instancia = new SesionUsuario();
    private static UsuarioDTO usuarioActual;

    private SesionUsuario() {
    }
    
    public static UsuarioDTO obtenerUsuario() {
        return usuarioActual;
    }

    public static ConductorDTO obtenerConductor() {
        return usuarioActual.getConductor();
    }

    public static synchronized SesionUsuario obtenerInstancia() {
        if (instancia == null) {
            instancia = new SesionUsuario();
        }
        return instancia;
    }

    public static void iniciarSesion(UsuarioDTO usuario) {
        SesionUsuario.usuarioActual = usuario;
    }
    
    public static boolean haySesionActiva() {
        return usuarioActual != null;
    }
    
    public static void cerrarSesion() {
        usuarioActual = null;
    }
    
}

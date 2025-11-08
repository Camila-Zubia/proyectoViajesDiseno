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
 class SesionUsuario {
    
    private static volatile UsuarioDTO usuarioActual;

    private SesionUsuario() {
    }
    
    public static synchronized void iniciarSesion(UsuarioDTO usuario){
        SesionUsuario.usuarioActual = usuario;
    }
    
    public static UsuarioDTO getUsuarioActual(){
        return usuarioActual;
    } 
    
    public static void cerrarSesion() {
        usuarioActual = null;
    }
    
    public static boolean haySesionActiva() {
        return usuarioActual != null;
    }
}

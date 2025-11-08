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
    
    private UsuarioDTO usuario;

    public UsuarioNegocio() {
        usuario = new UsuarioDTO("cperez", "1234");
    }

    public UsuarioDTO obtenerUsuario() {
        return usuario;
    }

    public void asignarConductor(ConductorDTO conductor) {
        this.usuario.setConductor(conductor);
    }
    
}

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
        ConductorDTO conductor = new ConductorDTO("carlos");
        usuario.setConductor(conductor);
    }

    public UsuarioDTO obtenerUsuario() {
        return usuario;
    }

    public ConductorDTO obtenerConductor() {
        return usuario.getConductor();
    }
    
}

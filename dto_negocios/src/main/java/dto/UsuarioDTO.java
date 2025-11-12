/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class UsuarioDTO {
    
    private String usuario;
    private String contraseña;
    private ConductorDTO conductor;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public ConductorDTO getConductor() {
        return conductor;
    }

    public void setConductor(ConductorDTO conductor) {
        this.conductor = conductor;
    }

    @Override
    public String toString() {
        return "Usuario = " + usuario + 
                ", contrase\u00f1a = " + contraseña;
    }
    
}

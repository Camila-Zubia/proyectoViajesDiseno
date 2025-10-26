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

    @Override
    public String toString() {
        return "UsuarioDTO" + 
                "\nusuario = " + usuario + 
                "\ncontrase\u00f1a = " + contraseña;
    }
    
}

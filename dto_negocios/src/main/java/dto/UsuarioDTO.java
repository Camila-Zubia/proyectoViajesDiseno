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
    private String contraseñaHaseada;
    private char[] contraseña;
    private ConductorDTO conductor;
    private PasajeroDTO pasajero;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String usuario, char[] contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public UsuarioDTO(String usuario, String contraseñaHaseada) {
        this.usuario = usuario;
        this.contraseñaHaseada = contraseñaHaseada;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseñaHaseada() {
        return contraseñaHaseada;
    }

    public void setContraseñaHaseada(String contraseñaHaseada) {
        this.contraseñaHaseada = contraseñaHaseada;
    }

    public char[] getContraseña() {
        return contraseña;
    }

    public void setContraseña(char[] contraseña) {
        this.contraseña = contraseña;
    }

    public ConductorDTO getConductor() {
        return conductor;
    }

    public void setConductor(ConductorDTO conductor) {
        this.conductor = conductor;
    }

    public PasajeroDTO getPasajero() {
        return pasajero;
    }

    public void setPasajero(PasajeroDTO pasajero) {
        this.pasajero = pasajero;
    }

    @Override
    public String toString() {
        return "Usuario = " + usuario + 
                ", contrase\u00f1a = " + contraseña;
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Controles;

import dto.ConductorDTO;
import dto.UsuarioDTO;
import dto.ViajeDTO;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.JPanel;

/**
 *
 * @author Camila Zubia 00000244825
 */
public interface IControlPantallas {
    
    public void configurarPanel(JPanel panel);
    
    public void mostrarMenuVehiculos();
    
    public void mostrarInicioSesion();
    
    public void mostrarMenuConductor();
    
    public void mostrarDatosViaje();
    
    public void mostrarDatosParada();
    
    public void mostrarSeleccionarPerfil();
    
    public void seleccionarVehiculo(dto.VehiculoDTO vehiculo);
    
    public void guardarDatosViaje(String origen, String destino, LocalDate fecha, LocalTime hora);
    
    public void confirmarViaje();
    
    public void agregarParada(String direccion, double precio);
    
    public boolean validarUsuario(UsuarioDTO usuario);
    
    public ConductorDTO nombreConductor();
    
    public void cerrarSesion();
    
    //Estos son los metodos del subsitema "Solicitar Reservacion"
    public void mostrarMenuPasajero();
}

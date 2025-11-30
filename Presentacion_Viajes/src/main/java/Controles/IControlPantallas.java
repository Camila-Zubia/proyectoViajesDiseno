/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Controles;

import dto.ConductorDTO;
import dto.ParadaDTO;
import dto.PasajeroDTO;
import dto.ReservacionDTO;
import dto.UsuarioDTO;
import dto.ViajeDTO;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
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
    
    public void mostrarParadasViaje();
    
    public void mostrarDatosReservacion();
    
    public List<ViajeDTO> obtenerViajes();
    
    public List<ParadaDTO> obtenerParadas();
    
    public void seleccionarViaje(ViajeDTO viaje);
    
    public void seleccionarParada(ParadaDTO parada);
    
    public void solicitarParada(String direccion);

    public ReservacionDTO confirmarReservacion();

    public ReservacionDTO obtenerReservacionTemporal();
    
    public PasajeroDTO nombrePasajero();
    
    //metodos subsistema "CancelarReservacion"
    public void mostrarReservaciones();
    
    public void mostrarCancelarReservacion();
    
    public List<ReservacionDTO> obtenerReservacionesDisponibles();

    public ReservacionDTO seleccionarReservacion(ReservacionDTO reservacion);

    public ReservacionDTO confirmarCancelacion();

    public ReservacionDTO obtenerReservacion();

    // Métodos para cancelar viaje
    public void mostrarDetallesViaje();

    public ViajeDTO obtenerViajeTemporal();

    public void confirmarCancelacionViaje();

}

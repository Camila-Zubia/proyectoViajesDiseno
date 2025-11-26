/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package solicitarReservacion;

import dto.ParadaDTO;
import dto.ReservacionDTO;
import dto.ViajeDTO;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface ISolicitarReservacion {
    
    public List<ViajeDTO> obtenerViajesDisponibles();
    
    public List<ParadaDTO> obtenerParadas();
    
    public void seleccionarViaje(ViajeDTO viaje);
    
    public void seleccionarParada(ParadaDTO parada);
    
    public void solicitarParada(String direccion);
    
    public ReservacionDTO confirmarReservacion();
    
    public ReservacionDTO obtenerReservacionTemporal();
    
}

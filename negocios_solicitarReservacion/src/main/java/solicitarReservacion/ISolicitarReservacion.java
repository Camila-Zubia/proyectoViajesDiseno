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
    
    public ViajeDTO seleccionarViaje(ViajeDTO viaje);
    
    public ParadaDTO seleccionarParada(ParadaDTO parada);
    
    public ParadaDTO solicitarParada(String direccion);
    
    public ReservacionDTO confirmarReservacion();
    
    public ReservacionDTO obtenerReservacionTemporal();
    
}

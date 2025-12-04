/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dto.ParadaDTO;
import dto.ReservacionDTO;
import dto.PasajeroDTO;
import dto.ViajeDTO;
import java.util.List;

/**
 *
 * @author Camila Zubia 00000244825
 */
public interface IViajeNegocio {
    
    public void registrarViaje(ViajeDTO viaje);
    
    public List<ParadaDTO> obtenerParadas(ViajeDTO viaje);

    public void registrarParada(ViajeDTO viaje, ParadaDTO parada);
    
    public List<ViajeDTO> obtenerTodosLosViajesDisponibles();
    
    public List<ReservacionDTO> obtenerReservacionesPorViaje(String viajeId);
    

    /**
     * Elimina (marca como inactivo) un viaje
     * @param idViaje ID del viaje
     * @return true si se eliminó correctamente
     */
    public boolean eliminarViaje(String idViaje);

    /**
     * Obtiene los detalles completos de un viaje
     * @param idViaje ID del viaje
     * @return ViajeDTO con los detalles
     */
    public ViajeDTO obtenerDetalleViaje(String idViaje);

    /**
     * Obtiene la lista de pasajeros de un viaje
     * @param idViaje ID del viaje
     * @return Lista de pasajeros
     */
    public List<PasajeroDTO> obtenerPasajeros(String idViaje);

}

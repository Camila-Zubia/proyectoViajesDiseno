/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dto.ParadaDTO;
import dto.PasajeroDTO;
import dto.ReservacionDTO;
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

    public boolean eliminarViaje(String idViaje);

    public ViajeDTO obtenerDetalleViaje(String idViaje);

    public List<PasajeroDTO> obtenerPasajeros(String idViaje);
    
}

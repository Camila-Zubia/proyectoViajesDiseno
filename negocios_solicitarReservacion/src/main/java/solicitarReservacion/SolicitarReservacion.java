/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
public class SolicitarReservacion implements ISolicitarReservacion{
    
    private final GestorSolicitud gestor;

    public SolicitarReservacion() {
        this.gestor = new GestorSolicitud();
    }

    @Override
    public List<ViajeDTO> obtenerViajesDisponibles() {
        return gestor.obtenerViajes();
    }
    
    @Override
    public List<ParadaDTO> obtenerParadas() {
        return gestor.obtenerParadas();
    }

    @Override
    public void seleccionarViaje(ViajeDTO viaje) {
        gestor.seleccionarViaje(viaje);
    }

    @Override
    public void seleccionarParada(ParadaDTO parada) {
        gestor.seleccionarParada(parada);
    }

    @Override
    public void solicitarParada(String direccion) {
        gestor.solicitarParada(direccion);
    }

    @Override
    public ReservacionDTO confirmarReservacion() {
        return gestor.confirmarReservacion();
    }

    @Override
    public ReservacionDTO obtenerReservacionTemporal() {
        return gestor.obtenerReservacionTemporal();
    }
    
}

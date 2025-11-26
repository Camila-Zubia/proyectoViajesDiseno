/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cancelarReservacion;

import dto.ReservacionDTO;
import factory.FabricaBOs;
import factory.IFabricaBOs;
import interfaces_solicitarReservacion.IPasajeroNegocio;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class GestorCancelaciones {
    
    private final IPasajeroNegocio pasajeroBO;
    private ReservacionDTO reservacionSeleccionada;

    public GestorCancelaciones() {
        IFabricaBOs fabrica = new FabricaBOs();
        this.pasajeroBO = fabrica.crearPasajeroNegocio();
    }
    
    public List<ReservacionDTO> obtenerReservacionesDisponibles(){
        return pasajeroBO.obtenerReservaciones();
    }
    
    public ReservacionDTO seleccionarReservacion(ReservacionDTO reservacion){
        return reservacionSeleccionada = reservacion;
    }
    
    public ReservacionDTO confirmarCancelacion(){
        if (reservacionSeleccionada == null) {
            throw new IllegalStateException("Debe seleccionar una reservacion antes de realizar la cancelación");
        }
        pasajeroBO.removerReservacion(reservacionSeleccionada);
        return reservacionSeleccionada;
    }
}

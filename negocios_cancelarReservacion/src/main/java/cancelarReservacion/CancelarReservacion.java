/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cancelarReservacion;

import dto.ReservacionDTO;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class CancelarReservacion implements ICancelarReservacion{

    private GestorCancelaciones gestor;

    public CancelarReservacion() {
        this.gestor = new GestorCancelaciones();
    }
    
    @Override
    public List<ReservacionDTO> obtenerReservacionesDisponibles() {
        return gestor.obtenerReservacionesDisponibles();
    }

    @Override
    public ReservacionDTO seleccionarReservacion(ReservacionDTO reservacion) {
       return gestor.seleccionarReservacion(reservacion);
    }

    @Override
    public ReservacionDTO confirmarCancelacion() {
        return gestor.confirmarCancelacion();
    }

    @Override
    public ReservacionDTO obtenerReservacion() {
        return gestor.obtenerReservacion();
    }
    
}

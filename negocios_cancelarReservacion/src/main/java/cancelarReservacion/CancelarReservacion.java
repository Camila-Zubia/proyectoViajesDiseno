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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ReservacionDTO seleccionarReservacion(ReservacionDTO reservacion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean confirmarCancelacion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

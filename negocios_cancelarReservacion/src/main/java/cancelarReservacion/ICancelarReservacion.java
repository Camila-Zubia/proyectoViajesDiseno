/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cancelarReservacion;

import dto.ReservacionDTO;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface ICancelarReservacion {
    
    public List<ReservacionDTO> obtenerReservacionesDisponibles();
    
    public ReservacionDTO seleccionarReservacion(ReservacionDTO reservacion);
    
    public ReservacionDTO confirmarCancelacion();
    
    public ReservacionDTO obtenerReservacion();
    
}

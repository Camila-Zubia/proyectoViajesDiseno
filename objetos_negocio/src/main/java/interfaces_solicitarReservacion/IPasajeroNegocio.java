/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces_solicitarReservacion;

import dto.ReservacionDTO;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IPasajeroNegocio {
    
    public void agregarReservacion(ReservacionDTO reservacion);
    
    public List<ReservacionDTO> obtenerReservaciones();
    
    public List<ReservacionDTO> removerReservacion(ReservacionDTO reservacion);
    
}

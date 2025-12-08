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
public interface IReservacionNegocio {
    
    public void gestionarSolicitud(ReservacionDTO reservacion, double precioAsignadoAParada, boolean aceptar);

    public List<ReservacionDTO> obtenerSolicitudesPendientes(String idViaje);
    
}

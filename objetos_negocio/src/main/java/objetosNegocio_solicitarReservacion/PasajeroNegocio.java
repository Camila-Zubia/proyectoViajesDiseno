/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio_solicitarReservacion;

import dto.ReservacionDTO;
import interfaces_solicitarReservacion.IPasajeroNegocio;
import java.util.List;
import utilidades.SesionUsuario;

/**
 *
 * @author Usuario
 */
public class PasajeroNegocio implements IPasajeroNegocio{

    public PasajeroNegocio() {
    }

    @Override
    public void agregarReservacion(ReservacionDTO reservacion) {
        if (validarNoExiste(SesionUsuario.obtenerPasajero().getReservaciones(), reservacion)) {
            SesionUsuario.obtenerPasajero().getReservaciones().add(reservacion);
        } else {
            throw new IllegalStateException("Ya existe una reservacion con los mismos datos.");
        }
    }

    private boolean validarNoExiste(List<ReservacionDTO> reservaciones, ReservacionDTO reservacion) {
        for (ReservacionDTO r : reservaciones) {
            if (r == reservacion) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<ReservacionDTO> obtenerReservaciones() {
        return SesionUsuario.obtenerPasajero().getReservaciones();
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio_solicitarReservacion;

import adaptadores.adaptadorReservacion;
import adaptadores.adaptadorVehiculo;
import adaptadores.adaptadorViaje;
import dto.EstatusReservacion;
import dto.ReservacionDTO;
import dto.ViajeDTO;
import interfaces_solicitarReservacion.IPasajeroNegocio;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.base_datos_viajes.dao.impl.PasajeroDAO;
import org.base_datos_viajes.dao.interfaces.IPasajeroDAO;
import org.base_datos_viajes.model.Reservacion;
import org.base_datos_viajes.model.Reservacion.Estatus;
import org.base_datos_viajes.model.Viaje;
import org.bson.types.ObjectId;
import utilidades.SesionUsuario;

/**
 *
 * @author Usuario
 */
public class PasajeroNegocio implements IPasajeroNegocio{

    private final IPasajeroDAO pasajeroDAO;
    
    public PasajeroNegocio() {
        this.pasajeroDAO = new PasajeroDAO();
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
        ObjectId pasajeroId = new ObjectId(SesionUsuario.obtenerPasajero().getId());

        List<Reservacion> todasLasReservaciones = pasajeroDAO.obtenerReservaciones(pasajeroId.toHexString());

        List<ReservacionDTO> reservacionesDisponibles = todasLasReservaciones.stream()
                .filter(r -> r.getEstatus() == Estatus.ACEPTADA)
                .map(adaptadorReservacion::toDTO)
                .collect(Collectors.toList());

        return reservacionesDisponibles;
    }
    
    @Override
    public List<ReservacionDTO> removerReservacion(ReservacionDTO reservacion) {
        reservacion.setEstatus(EstatusReservacion.CANCELADA);
        return SesionUsuario.obtenerPasajero().getReservaciones();
    }
    
}

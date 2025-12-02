/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio_solicitarReservacion;

import adaptadores.adaptadorReservacion;
import dto.EstatusReservacion;
import dto.ReservacionDTO;
import interfaces_solicitarReservacion.IPasajeroNegocio;
import java.util.List;
import java.util.stream.Collectors;
import org.base_datos_viajes.dao.interfaces.IPasajeroDAO;
import org.base_datos_viajes.dao.interfaces.IReservacionDAO;
import org.base_datos_viajes.model.Reservacion;
import org.base_datos_viajes.model.Reservacion.Estatus;
import org.bson.types.ObjectId;
import utilidades.SesionUsuario;

/**
 *
 * @author Usuario
 */
public class PasajeroNegocio implements IPasajeroNegocio{

    private final IPasajeroDAO pasajeroDAO;
    private final IReservacionDAO reservacionDAO;
    
    public PasajeroNegocio(IPasajeroDAO pasajeroDAO, IReservacionDAO reservacionDAO) {
        this.pasajeroDAO = pasajeroDAO;
        this.reservacionDAO = reservacionDAO;
    }

    @Override
    public void agregarReservacion(ReservacionDTO reservacion) {
        if (validarNoExiste(SesionUsuario.obtenerPasajero().getReservaciones(), reservacion)) {
            Reservacion r = adaptadorReservacion.toEntity(reservacion);
            reservacionDAO.save(r);
        } else {
            throw new IllegalStateException("Ya existe una reservacion con los mismos datos.");
        }
    }

    private boolean validarNoExiste(List<ReservacionDTO> reservaciones, ReservacionDTO reservacion) {
        if (reservaciones == null) {
            return true;
        }
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
        return obtenerReservaciones();
    }
    
}

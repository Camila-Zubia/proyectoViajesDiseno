/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio_solicitarReservacion;

import adaptadores.adaptadorPasajero;
import adaptadores.adaptadorReservacion;
import dto.EstatusReservacion;
import dto.PasajeroDTO;
import dto.ReservacionDTO;
import interfaces_solicitarReservacion.IPasajeroNegocio;
import java.util.List;
import java.util.stream.Collectors;
import org.base_datos_viajes.dao.interfaces.IPasajeroDAO;
import org.base_datos_viajes.dao.interfaces.IReservacionDAO;
import org.base_datos_viajes.exception.DatabaseException;
import org.base_datos_viajes.model.Pasajero;
import org.base_datos_viajes.model.Reservacion;
import org.base_datos_viajes.model.Reservacion.Estatus;
import org.bson.types.ObjectId;
import utilidades.SesionUsuario;

/**
 *
 * @author Camila Zubia 00000244825
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
        if (validarNoExiste(reservacion)) {
            Reservacion r = adaptadorReservacion.toEntity(reservacion);
            ObjectId pasajeroId = new ObjectId(SesionUsuario.obtenerPasajero().getId());
            r.setPasajeroId(pasajeroId);
            reservacionDAO.save(r);
        } else {
            throw new IllegalStateException("Ya tienes una reservación activa o pendiente para el viaje con ID seleccionado");
        }
    }

    private boolean validarNoExiste(ReservacionDTO reservacion) {

        if (reservacion.getViaje() == null || reservacion.getViaje().getId() == null) {
            return true;
        }

        String viajeIdNuevo = reservacion.getViaje().getId();
        ObjectId pasajeroId = new ObjectId(SesionUsuario.obtenerPasajero().getId());
        List<Reservacion> todasLasReservaciones = pasajeroDAO.obtenerReservaciones(pasajeroId.toHexString());

        if (todasLasReservaciones == null || todasLasReservaciones.isEmpty()) {
            return true;
        }

        boolean yaExisteReservacionActiva = todasLasReservaciones.stream()
                .map(adaptadorReservacion::toDTO)
                .anyMatch(r -> {
                    if (r == null || r.getViaje() == null || r.getViaje().getId() == null || r.getEstatus() == null) {
                        return false;
                    }
                    boolean mismoViaje = viajeIdNuevo.equals(r.getViaje().getId());
                    boolean estaActivaOPendiente = (r.getEstatus() == EstatusReservacion.ACEPTADA || r.getEstatus() == EstatusReservacion.ESPERA);

                    return mismoViaje && estaActivaOPendiente;
                });

        return !yaExisteReservacionActiva;
    }

    @Override
    public List<ReservacionDTO> obtenerReservaciones() {
        ObjectId pasajeroId = new ObjectId(SesionUsuario.obtenerPasajero().getId());

        List<Reservacion> todasLasReservaciones = pasajeroDAO.obtenerReservaciones(pasajeroId.toHexString());

        List<ReservacionDTO> reservacionesDisponibles = todasLasReservaciones.stream()
                .filter(r -> r.getEstatus() == Estatus.ACEPTADA || r.getEstatus() == Estatus.ESPERA)
                .map(adaptadorReservacion::toDTO)
                .collect(Collectors.toList());

        return reservacionesDisponibles;
    }

    @Override
    public List<ReservacionDTO> removerReservacion(ReservacionDTO reservacion) {
        if (reservacion.getTiempoRestante() < 7200) {
            PasajeroDTO pasajeroActual = SesionUsuario.obtenerPasajero();
            double nuevaCalificacion = pasajeroActual.getCalificacion() - 10;
            pasajeroActual.setCalificacion(nuevaCalificacion);

            try {
                Pasajero entidadPasajero = adaptadorPasajero.toEntity(pasajeroActual);
                pasajeroDAO.update(entidadPasajero);
            } catch (DatabaseException e) {
                System.err.println("Error al penalizar pasajero: " + e.getMessage());
            }
        }
        reservacion.setEstatus(EstatusReservacion.CANCELADA);
        Reservacion r = adaptadorReservacion.toEntity(reservacion);
        reservacionDAO.update(r);
        return obtenerReservaciones();
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio_solicitarReservacion;

import adaptadores.adaptadorReservacion;
import dto.EstatusReservacion;
import dto.ReservacionDTO;
import interfaces_solicitarReservacion.IReservacionNegocio;
import java.util.List;
import java.util.stream.Collectors;
import org.base_datos_viajes.dao.interfaces.IReservacionDAO;
import exceptiones.DatabaseException;
import org.base_datos_viajes.exception.DatabaseException;
import org.base_datos_viajes.model.Reservacion;
import org.bson.types.ObjectId;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class ReservacionNegocio implements IReservacionNegocio {

    private final IReservacionDAO reservacionDAO;

    public ReservacionNegocio(IReservacionDAO reservacionDAO) {
        this.reservacionDAO = reservacionDAO;
    }

    @Override
    public List<ReservacionDTO> obtenerSolicitudesPendientes(String idViaje) {
        List<Reservacion> reservaciones = reservacionDAO.encuentraPorIdViaje(new ObjectId(idViaje));
        return reservaciones.stream()
                .filter(r -> r.getEstatus() == Reservacion.Estatus.ESPERA)
                .map(adaptadorReservacion::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void gestionarSolicitud(ReservacionDTO reservacion, double precioAsignadoAParada, boolean aceptar) {
        if (reservacion == null || reservacion.getId() == null) {
            throw new IllegalArgumentException("La reservación no es válida.");
        }

        if (!aceptar) {
            reservacion.setEstatus(EstatusReservacion.RECHAZADA);
        } else {
            if (reservacion.getParada() != null) {
                reservacion.getParada().setPrecio(precioAsignadoAParada);
            }
            double precioBaseViaje = reservacion.getViaje().getPrecioTotal();
            reservacion.setPrecioTotal(precioBaseViaje + precioAsignadoAParada);
            reservacion.setEstatus(EstatusReservacion.ACEPTADA);
        }

        try {
            Reservacion entidad = adaptadorReservacion.toEntity(reservacion);
            reservacionDAO.update(entidad);
        } catch (DatabaseException e) {
            throw new RuntimeException("Error al actualizar la solicitud: " + e.getMessage());
        }
    }
}

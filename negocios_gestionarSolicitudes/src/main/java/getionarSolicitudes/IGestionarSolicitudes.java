
package getionarSolicitudes;

import dto.ReservacionDTO;
import java.util.List;

/**
 * @author ferch
 *  Fachada para el caso de uso Gestionar Solicitudes.
 */
public interface IGestionarSolicitudes {
    /**
     * Obtiene la lista de solicitudes de reservacion pendientes para un viaje dado.
     * @param viajeId
     * @return 
     */
    public List<ReservacionDTO> obtenerSolicitudesPendientes(String viajeId);

    public ReservacionDTO aceptarSolicitud(ReservacionDTO reservacion);

    public ReservacionDTO rechazarSolicitud(ReservacionDTO reservacion);

    public ReservacionDTO proponerPrecio(ReservacionDTO reservacion);
}

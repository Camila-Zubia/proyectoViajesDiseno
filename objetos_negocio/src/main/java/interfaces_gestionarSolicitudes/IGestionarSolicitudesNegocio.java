
package interfaces_gestionarSolicitudes;
import dto.ReservacionDTO;
import dto.ViajeDTO;
import java.util.List;

/**
 * Interfaz que define las operaciones de negocio para la gestion de solicitudes de reservacion
 * por parte del conductor.
 * @author ferch
 */
public interface IGestionarSolicitudesNegocio {
    /**
     * Obtiene la lista de reservaciones en estado ESPERA para un viaje especifico.
     * @param viajeId ID del viaje del conductor.
     * @return Lista de ReservacionDTO en espera.
     */
    public List<ReservacionDTO> obtenerSolicitudesPendientes(String viajeId);

    /**
     * Procesa la aceptacion de una reservacion. 
     * Valida la capacidad del vehiculo y actualiza el estado del viaje.
     * @param reservacion ReservacionDTO a aceptar.
     * @return ReservacionDTO actualizada.
     */
    public ReservacionDTO aceptarSolicitud(ReservacionDTO reservacion);

    /**
     * Procesa el rechazo de una reservacion.
     * @param reservacion ReservacionDTO a rechazar.
     * @return ReservacionDTO actualizada.
     */
    public ReservacionDTO rechazarSolicitud(ReservacionDTO reservacion);
    
    /**
     * Obtiene los detalles completos de un viaje.
     * @param viajeId ID del viaje.
     * @return ViajeDTO.
     */
    public ViajeDTO obtenerDetallesViaje(String viajeId);

    /**
     * Procesa el precio propuesto por el conductor para una parada de ruta no existente.
     * Mantiene el estado ESPERA, pero actualiza el precio.
     * @param reservacion ReservacionDTO con el precio actualizado.
     * @return ReservacionDTO con el nuevo precio propuesto.
     */
    public ReservacionDTO proponerPrecio(ReservacionDTO reservacion);
}

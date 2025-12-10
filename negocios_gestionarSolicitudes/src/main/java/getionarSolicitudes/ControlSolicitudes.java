
package getionarSolicitudes;

import dto.ReservacionDTO;
import factory.FabricaBOs;
import factory.IFabricaBOs;
import interfaces_gestionarSolicitudes.IGestionarSolicitudesNegocio;
import java.util.List;

/**
 * @author ferch
 * Clase que maneja el flujo de gestion de solicitudes para un viaje.
 */
public class ControlSolicitudes {
    private final IGestionarSolicitudesNegocio solicitudesBO;

    public ControlSolicitudes() {
        IFabricaBOs fabrica = new FabricaBOs();
        // Se asume que FabricaBOs.crearGestionarSolicitudesNegocio ya existe
        this.solicitudesBO = fabrica.crearGestionarSolicitudesNegocio(); 
    }
    
    public List<ReservacionDTO> obtenerSolicitudesPendientes(String viajeId) {
        return solicitudesBO.obtenerSolicitudesPendientes(viajeId);
    }
    
    public ReservacionDTO aceptarSolicitud(ReservacionDTO reservacion) {
        return solicitudesBO.aceptarSolicitud(reservacion);
    }

    public ReservacionDTO rechazarSolicitud(ReservacionDTO reservacion) {
        return solicitudesBO.rechazarSolicitud(reservacion);
    }
    
    public ReservacionDTO proponerPrecio(ReservacionDTO reservacion) {
        return solicitudesBO.proponerPrecio(reservacion);
    }
}

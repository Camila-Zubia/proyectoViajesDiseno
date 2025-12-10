
package getionarSolicitudes;

import dto.ReservacionDTO;
import java.util.List;

/**
 *
 * @author ferch
 */
public class GestionarSolicitudes implements IGestionarSolicitudes{
 private final ControlSolicitudes control;
 
 public GestionarSolicitudes() {
        this.control = new ControlSolicitudes();
    }
 
    
    @Override
    public List<ReservacionDTO> obtenerSolicitudesPendientes(String viajeId) {
        return control.obtenerSolicitudesPendientes(viajeId);
    }

    @Override
    public ReservacionDTO aceptarSolicitud(ReservacionDTO reservacion) {
        return control.aceptarSolicitud(reservacion);
    }

    @Override
    public ReservacionDTO rechazarSolicitud(ReservacionDTO reservacion) {
        return control.rechazarSolicitud(reservacion);
    }

    @Override
    public ReservacionDTO proponerPrecio(ReservacionDTO reservacion) {
        return control.proponerPrecio(reservacion);
    }
    
}

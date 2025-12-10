
package objetosNegocio_gestionarSolicitudes;

import adaptadores.adaptadorReservacion;
import adaptadores.adaptadorVehiculo;
import adaptadores.adaptadorViaje;
import dto.ReservacionDTO;
import dto.ViajeDTO;
import interfaces_gestionarSolicitudes.IGestionarSolicitudesNegocio;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.base_datos_viajes.dao.impl.ViajeDAO;
import org.base_datos_viajes.dao.interfaces.IConductorDAO;
import org.base_datos_viajes.dao.interfaces.IReservacionDAO;
import org.base_datos_viajes.model.Reservacion;
import org.base_datos_viajes.model.Reservacion.Estatus;
import org.base_datos_viajes.model.Vehiculo;
import org.base_datos_viajes.model.Viaje;
import org.bson.types.ObjectId;

/**
  * Logica de negocio para la gestion de solicitudes de reservacion por parte del conductor.
 * @author ferch
 */
public class GestionarSolicitudesNegocio implements IGestionarSolicitudesNegocio{
    private final ViajeDAO viajeDAO;
    private final IReservacionDAO reservacionDAO;
    private final IConductorDAO conductorDAO;

    public GestionarSolicitudesNegocio(ViajeDAO viajeDAO, IReservacionDAO reservacionDAO, IConductorDAO conductorDAO) {
        this.viajeDAO = viajeDAO;
        this.reservacionDAO = reservacionDAO;
        this.conductorDAO = conductorDAO;
    }
    
    @Override
    public ViajeDTO obtenerDetallesViaje(String viajeId) {
        //Metodo para obtener detalles del viaje
        try {
            Optional<Viaje> optionalViaje = viajeDAO.findById(new ObjectId(viajeId));
            if (!optionalViaje.isPresent()) {
                return null;
            }
            Viaje viajeEntidad = optionalViaje.get();
            ViajeDTO viajeDTO = adaptadorViaje.toDTO(viajeEntidad);
            
            // cargar el VehiculoDTO
            if (viajeEntidad.getConductorId() != null && viajeEntidad.getVehiculoId() != null) {
                // Obtener vehiculos del conductor para encontrar el vehiculo del viaje
                List<Vehiculo> vehiculosConductor = conductorDAO.obtenerVehiculos(viajeEntidad.getConductorId().toHexString());
                
                Optional<Vehiculo> vehiculoEntidad = vehiculosConductor.stream()
                        .filter(v -> v.getId().equals(viajeEntidad.getVehiculoId()))
                        .findFirst();
                
                if (vehiculoEntidad.isPresent()) {
                    viajeDTO.setVehiculo(adaptadorVehiculo.toDTO(vehiculoEntidad.get()));
                }
            }
            
            return viajeDTO;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener detalles del viaje: " + e.getMessage(), e);
        }
    }

    @Override
    public List<ReservacionDTO> obtenerSolicitudesPendientes(String viajeId) {
        // reutilizacion: usa ReservacionDAO.encuentraPorIdViaje y filtra por ESPERA
        try {
            ObjectId id = new ObjectId(viajeId);
            List<Reservacion> entidades = reservacionDAO.encuentraPorIdViaje(id);
            
            // Filtra solo las que estan en estado ESPERA (las solicitudes activas)
            return entidades.stream()
                    .filter(r -> r.getEstatus() == Estatus.ESPERA)
                    .map(adaptadorReservacion::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener solicitudes pendientes: " + e.getMessage(), e);
        }
    }

    @Override
    public ReservacionDTO aceptarSolicitud(ReservacionDTO reservacionDTO) {
        // 1. Capacidad, 2. Actualizar Reserva a ACEPTADA, 3. Actualizar Viaje (+1 asiento)
        if (reservacionDTO.getId() == null || reservacionDTO.getViaje() == null) {
            throw new IllegalArgumentException("Reservacion o Viaje invalido.");
        }
        
        try {
            ViajeDTO viaje = obtenerDetallesViaje(reservacionDTO.getViaje().getId());
            
            if (viaje == null || viaje.getVehiculo() == null) { 
                throw new IllegalStateException("Detalles del viaje o del vehículo no disponibles para verificación de capacidad.");
            }
            
            if (viaje.getCantidadPasajeros() >= viaje.getVehiculo().getCapacidad()) {
                throw new IllegalStateException("Capacidad maxima del vehículo alcanzada. No se puede aceptar la solicitud.");
            }

            // obtener la entidad existente antes de actualizar para asegurar ParadaId y ViajeId
            Reservacion entidadExistente = reservacionDAO.findById(new ObjectId(reservacionDTO.getId()))
                .orElseThrow(() -> new IllegalStateException("Reservacion no encontrada al aceptar."));
            
            // actualizar estado de Reservacion a ACEPTADA en la entidad existente
            entidadExistente.setEstatus(Reservacion.Estatus.ACEPTADA);
            reservacionDAO.update(entidadExistente);

            // Actualizar cantidad de pasajeros en el Viaje
            Viaje viajeEntidad = viajeDAO.findById(new ObjectId(viaje.getId()))
                    .orElseThrow(() -> new IllegalStateException("Viaje no encontrado al actualizar asientos."));
            viajeEntidad.setCantidadPasajeros(viaje.getCantidadPasajeros() + 1);
            viajeDAO.update(viajeEntidad);
            
            return adaptadorReservacion.toDTO(entidadExistente);

        } catch (Exception e) {
            throw new RuntimeException("Error al aceptar solicitud: " + e.getMessage(), e);
        }
    }

    @Override
    public ReservacionDTO rechazarSolicitud(ReservacionDTO reservacionDTO) {
        // Solo actualiza el estado a RECHAZADA.
        if (reservacionDTO.getId() == null) {
            throw new IllegalArgumentException("Reservacion invalida.");
        }
        
        try {
            //cargar la entidad existente para asegurar que tiene ViajeId y ParadaId
            ObjectId reservacionId = new ObjectId(reservacionDTO.getId());
            Reservacion entidad = reservacionDAO.findById(reservacionId)
                    .orElseThrow(() -> new IllegalStateException("Reservacion no encontrada al rechazar."));

            // actualizar el estado a RECHAZADA en la entidad existente
            entidad.setEstatus(Reservacion.Estatus.RECHAZADA);
            reservacionDAO.update(entidad);

            return adaptadorReservacion.toDTO(entidad);
        } catch (Exception e) {
            throw new RuntimeException("Error al rechazar solicitud: " + e.getMessage(), e);
        }
    }

    @Override
    public ReservacionDTO proponerPrecio(ReservacionDTO reservacionDTO) {
        // Actualiza el precio propuesto. Mantiene el estado ESPERA para que el pasajero reaccione.
        if (reservacionDTO.getId() == null) {
            throw new IllegalArgumentException("Reservacion invalida.");
        }
        
        try {
            ObjectId reservacionId = new ObjectId(reservacionDTO.getId());
            Reservacion entidad = reservacionDAO.findById(reservacionId)
                    .orElseThrow(() -> new IllegalStateException("Reservacion no encontrada."));

            // Actualizamos el precio total en la entidad
            entidad.setPrecioTotal(reservacionDTO.getPrecioTotal());
            reservacionDAO.update(entidad);

            // Retornamos el DTO actualizado con el precio propuesto
            return adaptadorReservacion.toDTO(entidad);
            
        } catch (Exception e) {
            throw new RuntimeException("Error al proponer nuevo precio: " + e.getMessage(), e);
        }
    }
}

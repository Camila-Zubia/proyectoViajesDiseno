
package objetosNegocio_editarViaje;

import adaptadores.adaptadorParada;
import adaptadores.adaptadorViaje;
import dto.ParadaDTO;
import dto.ViajeDTO;
import interfaces_editarViaje.IEditarViajeNegocio;
import java.util.List;
import java.util.Optional;
import org.base_datos_viajes.dao.impl.ViajeDAO;
import org.base_datos_viajes.dao.interfaces.IReservacionDAO;
import org.base_datos_viajes.model.Reservacion;
import org.base_datos_viajes.model.Viaje;
import org.bson.types.ObjectId;

/**
 * @author ferch
 * Logica de negocio para la edicion de viajes
 */
public class EditarViajeNegocio implements IEditarViajeNegocio{
     private final ViajeDAO viajeDAO;
    private final IReservacionDAO reservacionDAO;

    public EditarViajeNegocio(ViajeDAO viajeDAO, IReservacionDAO reservacionDAO) {
        this.viajeDAO = viajeDAO;
        this.reservacionDAO = reservacionDAO;
    }
    
     @Override
    public ViajeDTO actualizarDatosGenerales(ViajeDTO viajeModificado) {
        if (viajeModificado.getId() == null) {
            throw new IllegalArgumentException("El ID del viaje a editar es obligatorio.");
        }

        try {
            ObjectId viajeId = new ObjectId(viajeModificado.getId());
            Optional<Viaje> optionalViaje = viajeDAO.findById(viajeId);

            if (optionalViaje.isEmpty()) {
                throw new IllegalStateException("Viaje no encontrado.");
            }

            Viaje viajeEntidad = optionalViaje.get();

            // Validaciones de edicionn segura - si la ruta o fecha/hora cambiaron
            // Se debe validar que no haya reservaciones aceptadas si se cambia la fecha, hora o destino/origen.
            if (!validarEdicionSegidaPorCambio(viajeEntidad, viajeModificado)) {
                 throw new IllegalStateException("No se puede modificar los datos del viaje si ya hay reservaciones aceptadas.");
            }
            
            // Mapeo y actualizacion de datos generales
            viajeEntidad.setOrigen(viajeModificado.getOrigen());
            viajeEntidad.setDestino(viajeModificado.getDestino());
            viajeEntidad.setPrecioTotal(viajeModificado.getPrecioTotal());
            viajeEntidad.setFecha(viajeModificado.getFecha());
            viajeEntidad.setHora(viajeModificado.getHora());

            viajeDAO.update(viajeEntidad);
            return adaptadorViaje.toDTO(viajeEntidad);

        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar datos generales del viaje: " + e.getMessage(), e);
        }
    }

    @Override
    public ViajeDTO actualizarParadas(String viajeId, List<ParadaDTO> paradas) {
        if (viajeId == null || paradas == null || paradas.isEmpty()) {
             throw new IllegalArgumentException("El ID del viaje y la lista de paradas son obligatorios.");
        }
        if (paradas.size() < 1) {
            throw new IllegalArgumentException("El viaje debe tener al menos una parada (Origen).");
        }

        try {
            ObjectId id = new ObjectId(viajeId);
            Optional<Viaje> optionalViaje = viajeDAO.findById(id);

            if (optionalViaje.isEmpty()) {
                throw new IllegalStateException("Viaje no encontrado.");
            }

            Viaje viajeEntidad = optionalViaje.get();
            
            // Validaciones de edicion segura - si la lista de paradas es diferente
            // Ya que el destino se puede deducir de la ultima parada, y la lista es crítica,
            // se debe verificar la edicion segura.
            if (!validarEdicionSegura(viajeId)) {
                // Validacion estricta para paradas.
                boolean paradasCambiaron = !compararParadas(viajeEntidad.getParadas(), paradas);
                if (paradasCambiaron) {
                     throw new IllegalStateException("No se pueden modificar las paradas si ya hay reservaciones aceptadas o en espera.");
                }
            }

            // 2. Mapeo y persistencia
            List<org.base_datos_viajes.model.Parada> paradasEntidad = paradas.stream()
                .map(adaptadorParada::toEntity)
                .collect(java.util.stream.Collectors.toList());
            
            viajeEntidad.setParadas(paradasEntidad);
            
            // Actualizar la lista en la BD
            viajeDAO.update(viajeEntidad);
            
            return adaptadorViaje.toDTO(viajeEntidad);

        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la lista de paradas del viaje: " + e.getMessage(), e);
        }
    }
    
    @Override
    public boolean validarEdicionSegura(String viajeId) {
        try {
            ObjectId id = new ObjectId(viajeId);
            List<Reservacion> reservaciones = reservacionDAO.encuentraPorIdViaje(id);
            
            // Si hay alguna reservaciin aceptada o en espera, no es seguro editar.
            return reservaciones.stream()
                .noneMatch(r -> r.getEstatus() == Reservacion.Estatus.ACEPTADA || r.getEstatus() == Reservacion.Estatus.ESPERA);
                
        } catch (Exception e) {
            System.err.println("Error al validar reservaciones para edicion: " + e.getMessage());
            return false;
        }
    }

    /**
     * Compara si hubo cambios en los campos criticos (fecha, hora, origen o destino).
     */
    private boolean validarEdicionSegidaPorCambio(Viaje original, ViajeDTO modificado) {
        // Chequeo rapido si solo se cambio el precio
        if (original.getFecha().equals(modificado.getFecha()) &&
            original.getHora().equals(modificado.getHora()) &&
            original.getOrigen().equals(modificado.getOrigen()) &&
            original.getDestino().equals(modificado.getDestino())) {
            
            return true; // No hubo cambio en ruta/fecha/hora, es seguro.
        }
        
        // Hubo cambio en ruta/fecha/hora, se requiere validacion de reservaciones.
        return validarEdicionSegura(original.getId().toHexString());
    }
    
    /**
     * Compara dos listas de paradas solo por direccion y precio.
     */
    private boolean compararParadas(List<org.base_datos_viajes.model.Parada> entidades, List<ParadaDTO> dtos) {
        if (entidades.size() != dtos.size()) return false;
        
        for (int i = 0; i < entidades.size(); i++) {
            if (!entidades.get(i).getDireccion().equals(dtos.get(i).getDirección()) ||
                entidades.get(i).getPrecio() != dtos.get(i).getPrecio()) {
                return false;
            }
        }
        return true;
    }
    
}

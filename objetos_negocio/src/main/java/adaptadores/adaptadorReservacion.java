package adaptadores;

import dto.EstatusReservacion;
import dto.ParadaDTO;
import dto.ReservacionDTO;
import dto.ViajeDTO;
import java.util.Optional;
import org.base_datos_viajes.dao.impl.ParadaDAO;
import org.base_datos_viajes.dao.impl.ViajeDAO;
import org.base_datos_viajes.model.Parada;
import org.base_datos_viajes.model.Reservacion;
import org.base_datos_viajes.model.Viaje;
import org.bson.types.ObjectId;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class adaptadorReservacion {
    
    private static final ViajeDAO viajeDAO = new ViajeDAO();
    private static final ParadaDAO paradaDAO = new ParadaDAO();

    public static Reservacion toEntity(ReservacionDTO dto) {
        Reservacion.Estatus estatusEntidad = 
            Reservacion.Estatus.valueOf(dto.getEstatus().name());

        Reservacion entidad = new Reservacion(
                dto.getPrecioTotal(),
                dto.getTiempoRestante(),
                estatusEntidad
        );

        if (dto.getViaje() != null && dto.getViaje().getId() != null) {
            entidad.setViajeId(new ObjectId(dto.getViaje().getId()));
        } else {
            throw new IllegalArgumentException("La reservación debe tener un Viaje válido asociado.");
        }

        if (dto.getParada() != null && dto.getParada().getId() != null) {
            entidad.setParadaId(new ObjectId(dto.getParada().getId()));
        } else {
            throw new IllegalArgumentException("La reservación debe tener una Parada válida asociada.");
        }

        return entidad;
    }
    
    public static ReservacionDTO toDTO(Reservacion entidad) {
        ReservacionDTO dto = new ReservacionDTO();

        if (entidad.getId() != null) {
            dto.setId(entidad.getId().toString());
        }

        EstatusReservacion estatusDTO
                = EstatusReservacion.valueOf(entidad.getEstatus().name());

        dto.setEstatus(estatusDTO);
        dto.setPrecioTotal(entidad.getPrecioTotal());
        dto.setTiempoRestante(entidad.getTiempoRestante());
        
        if (entidad.getViajeId() != null) {
            Optional<Viaje> optionalViaje = viajeDAO.findById(entidad.getViajeId());
            if (optionalViaje.isPresent()) {
                ViajeDTO viajeDTO = adaptadorViaje.toDTO(optionalViaje.get());
                dto.setViaje(viajeDTO);
            }
        }
        
        if (entidad.getParadaId() != null) {
            Optional<Parada> optionalParada = paradaDAO.findById(entidad.getParadaId());
            if (optionalParada.isPresent()) {
                ParadaDTO paradaDTO = adaptadorParada.toDTO(optionalParada.get());
                dto.setParada(paradaDTO);
            }
        }

        return dto;
    }
}

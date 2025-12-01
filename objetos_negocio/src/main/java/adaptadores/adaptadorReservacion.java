package adaptadores;

import dto.EstatusReservacion;
import dto.ReservacionDTO;
import org.base_datos_viajes.model.Reservacion;
import org.bson.types.ObjectId;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class adaptadorReservacion {

    public static Reservacion toEntity(ReservacionDTO dto) {
        
        Reservacion.Estatus estatusEntidad
                = Reservacion.Estatus.valueOf(dto.getEstatus().name());

        Reservacion entidad = new Reservacion(
                dto.getPrecioTotal(),
                dto.getTiempoRestante(),
                estatusEntidad
        );

        if (dto.getId() != null) {
            entidad.setId(new ObjectId(dto.getId()));
        }
        entidad.setViajeId(new ObjectId(dto.getViaje().getId()));
        entidad.setParadaId(new ObjectId(dto.getParada().getId()));

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

        return dto;
    }
}

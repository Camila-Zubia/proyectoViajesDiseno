package adaptadores;

import dto.ParadaDTO;
import dto.ViajeDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.base_datos_viajes.model.Parada;
import org.base_datos_viajes.model.Viaje;
import org.bson.types.ObjectId;

/**
 *
 * @author ferch
 */
public class adaptadorViaje {

    public static Viaje toEntity(ViajeDTO dto, ObjectId idConductor, ObjectId idVehiculo) {

        Viaje entidad = new Viaje(
                "Viaje de " + dto.getOrigen() + " a " + dto.getDestino(),
                dto.getDestino(),
                dto.getOrigen(),
                dto.getFecha(),
                dto.getHora(),
                dto.getPrecioTotal()
        );

        if (dto.getId() != null) {
            entidad.setId(new ObjectId(dto.getId()));
        }

        entidad.setConductorId(idConductor);
        entidad.setVehiculoId(idVehiculo);

        if (dto.getParadas() != null) {
            List<Parada> paradasEntidad = dto.getParadas().stream()
                    .map(adaptadorParada::toEntity)
                    .collect(Collectors.toList());
            entidad.setParadas(paradasEntidad);
        } else {
            entidad.setParadas(new java.util.ArrayList<>());
        }
        return entidad;
    }

    public static ViajeDTO toDTO(Viaje entidad) {
        ViajeDTO dto = new ViajeDTO();

        if (entidad.getId() != null) {
            dto.setId(entidad.getId().toString());
        }

        dto.setFecha(entidad.getFecha());
        dto.setHora(entidad.getHora());

        dto.setOrigen(entidad.getOrigen());
        dto.setDestino(entidad.getDestino());
        dto.setPrecioTotal(entidad.getPrecioTotal());

        // Copiar los campos nuevos
        dto.setEstaActivo(entidad.isEstaActivo());
        dto.setCantidadPasajeros(entidad.getCantidadPasajeros());

        if (entidad.getParadas() != null) {
            List<ParadaDTO> paradasDTO = entidad.getParadas().stream()
                    .map(adaptadorParada::toDTO)
                    .collect(Collectors.toList());
            dto.setParadas(paradasDTO);
        } else {
            dto.setParadas(new java.util.ArrayList<>());
        }

        return dto;
    }
}


package adaptadores;

import dto.ConductorDTO;
import dto.VehiculoDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.base_datos_viajes.model.Conductor;
import org.base_datos_viajes.model.Vehiculo;
import org.bson.types.ObjectId;

/**
 *
 * @author ferch
 */
public class adaptadorConductor {
    public static ConductorDTO toDTO(Conductor entidad) {
        ConductorDTO dto = new ConductorDTO();
        
        if (entidad.getId() != null) {
             dto.setId(entidad.getId().toString());
        }
        
        dto.setNombre(entidad.getNombre());
        dto.setCalificación((int) entidad.getCalificacion());

        if (entidad.getVehiculos() != null) {
            List<VehiculoDTO> vehiculosDTO = entidad.getVehiculos().stream()
                    .map(adaptadorVehiculo::toDTO)
                    .collect(Collectors.toList());
            dto.setVehiculos(vehiculosDTO);
        } else {
            dto.setVehiculos(new java.util.ArrayList<>());
        }
        
        return dto;
    }

    public static Conductor toEntity(ConductorDTO dto) {
        List<Vehiculo> vehiculosEntidad = dto.getVehiculos().stream()
                .map(adaptadorVehiculo::toEntity)
                .collect(Collectors.toList());

        Conductor entidad = new Conductor(
            dto.getNombre(), 
            dto.getCalificación(), 
            vehiculosEntidad
        );
        
        if (dto.getId() != null) {
            entidad.setId(new ObjectId(dto.getId()));
        }

        return entidad;
    }
    
}

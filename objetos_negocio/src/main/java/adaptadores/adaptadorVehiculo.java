
package adaptadores;

import dto.VehiculoDTO;
import org.base_datos_viajes.model.Vehiculo;
import org.bson.types.ObjectId;

/**
 *
 * @author ferch
 */
public class adaptadorVehiculo {
    public static VehiculoDTO toDTO(Vehiculo entidad) {
        VehiculoDTO dto = new VehiculoDTO();

        if (entidad.getId() != null) { 
             dto.setId(entidad.getId().toString()); 
        }
        
        dto.setModelo(entidad.getModelo());
        dto.setPlacas(entidad.getPlacas());
        dto.setMarca(entidad.getMarca());
        dto.setColor(entidad.getColor());
        dto.setCapacidad(entidad.getCapacidad());
        return dto;
    }

    public static Vehiculo toEntity(VehiculoDTO dto) {
        Vehiculo entidad = new Vehiculo(
            dto.getMarca(),
            dto.getModelo(),
            dto.getColor(),
            null, 
            dto.getPlacas(),
            dto.getCapacidad()
        );

        if (dto.getId() != null) {
            entidad.setId(new ObjectId(dto.getId())); 
        }
        return entidad;
    }
}

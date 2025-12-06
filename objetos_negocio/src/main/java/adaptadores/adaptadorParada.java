
package adaptadores;

import dto.ParadaDTO;
import org.base_datos_viajes.model.Parada;
import org.bson.types.ObjectId;

/**
 *
 * @author ferch
 */
public class adaptadorParada {
    public static ParadaDTO toDTO(Parada entidad) {
        ParadaDTO dto = new ParadaDTO();
        
        dto.setDirección(entidad.getDireccion());
        dto.setPrecio(entidad.getPrecio());
        return dto;
    }

    public static Parada toEntity(ParadaDTO dto) {
        Parada entidad = new Parada(
            dto.getDirección(),
            dto.getPrecio()
        );

        if (dto.getId() != null && !dto.getId().isBlank()) {
            entidad.setId(new ObjectId(dto.getId()));
        }else{
            entidad.setId(new ObjectId());
        }

        return entidad;
    }
}

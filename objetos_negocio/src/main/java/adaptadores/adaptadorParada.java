
package adaptadores;

import dto.ParadaDTO;
import org.base_datos_viajes.model.Parada;

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
        return entidad;
    }
}


package adaptadores;

import dto.UsuarioDTO;
import org.base_datos_viajes.model.Usuario;
import org.bson.types.ObjectId;

/**
 *
 * @author ferch
 */
public class adaptadorUsuario {
    public static UsuarioDTO toDTO(Usuario entidad) {
        UsuarioDTO dto = new UsuarioDTO();
        
        if (entidad.getId() != null) {
             dto.setId(entidad.getId().toString()); 
        }
        dto.setUsuario(entidad.getUsuario());
        dto.setContraseñaHaseada(entidad.getContraseña()); 

        return dto;
    }
    
    public static Usuario toEntity(UsuarioDTO dto) {
        Usuario entidad = new Usuario();
        
        if (dto.getId() != null) {
            entidad.setId(new ObjectId(dto.getId()));
        }
        entidad.setUsuario(dto.getUsuario());
        entidad.setContraseña(dto.getContraseñaHaseada());
        
        if (dto.getConductor() != null && dto.getConductor().getId() != null) {
        entidad.setConductorId(new ObjectId(dto.getConductor().getId()));
    }

        return entidad;
    }
}

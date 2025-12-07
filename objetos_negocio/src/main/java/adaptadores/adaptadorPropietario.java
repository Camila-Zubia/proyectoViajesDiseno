/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adaptadores;

import dto.PropietarioDTO;
import dto.VehiculoDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.base_datos_viajes.model.Propietario;
import org.base_datos_viajes.model.Vehiculo;
import org.bson.types.ObjectId;

/**
 *
 * @author adell
 */
public class adaptadorPropietario {

    public static PropietarioDTO toDTO(Propietario entidad) {
        PropietarioDTO dto = new PropietarioDTO();
        if (entidad.getId() != null) {
            dto.setId(entidad.getId().toString());
        }

        dto.setCurp(entidad.getCurp());
        dto.setNombre(entidad.getNombre());
        dto.setNss(entidad.getNss());
        dto.setRfc(entidad.getRfc());

        if (entidad.getListaVehiculos() != null) {
            List<VehiculoDTO> vehiculosDTO = entidad.getListaVehiculos().stream()
                    .map(adaptadorVehiculo::toDTO)
                    .collect(Collectors.toList());
            dto.setListaVehiculos(vehiculosDTO);
        } else {
            dto.setListaVehiculos(new java.util.ArrayList<>());
        }

        return dto;
    }

    public static Propietario toEntity(PropietarioDTO dto) {
        List<Vehiculo> vehiculosEntidad = dto.getListaVehiculos().stream()
                .map(adaptadorVehiculo::toEntity)
                .collect(Collectors.toList());

        Propietario entidad = new Propietario(
                dto.getNombre(),
                dto.getCurp(),
                dto.getRfc(),
                dto.getNss(),
                vehiculosEntidad
        );
        
        if (dto.getId() != null) {
            entidad.setId(new ObjectId(dto.getId()));
        }
        
        return entidad;
        
    
    }

}

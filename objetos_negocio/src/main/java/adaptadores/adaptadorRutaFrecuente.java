/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adaptadores;

import dto.RutaFrecuenteDTO;
import org.base_datos_viajes.model.RutaFrecuente;
import org.bson.types.ObjectId;

/**
 *
 * @author adell
 */
public class adaptadorRutaFrecuente {
    public static RutaFrecuente toEntity(RutaFrecuenteDTO dto, ObjectId idConductor) {

        RutaFrecuente entidad = new RutaFrecuente(
                dto.getNombre(),
                "Viaje de " + dto.getOrigen() + " a " + dto.getDestino(),
                dto.getDestino(),
                dto.getOrigen(),
                dto.getFecha(),
                dto.getHora(),
                dto.getPrecioTotal()
        );

        if (dto.getId() != null) {
            entidad.setId(new ObjectId(dto.getId())); // id del viaje
        }

        entidad.setConductorId(idConductor);

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

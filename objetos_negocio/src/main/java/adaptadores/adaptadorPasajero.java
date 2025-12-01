/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adaptadores;

import dto.PasajeroDTO;
import dto.ReservacionDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.base_datos_viajes.model.Pasajero;
import org.base_datos_viajes.model.Reservacion;
import org.bson.types.ObjectId;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class adaptadorPasajero {
    
    public static PasajeroDTO toDTO(Pasajero entidad) {
        PasajeroDTO dto = new PasajeroDTO();

        if (entidad.getId() != null) {
            dto.setId(entidad.getId().toString());
        }

        dto.setNombre(entidad.getNombre());
        dto.setCalificacion((double) entidad.getCalificacion());

        if (entidad.getReservaciones()!= null) {
            List<ReservacionDTO> reservacionesDTO = entidad.getReservaciones().stream()
                    .map(adaptadorReservacion::toDTO)
                    .collect(Collectors.toList());
            dto.setReservaciones(reservacionesDTO);
        } else {
            dto.setReservaciones(new java.util.ArrayList<>());
        }

        return dto;
    }

    public static Pasajero toEntity(PasajeroDTO dto) {
        Pasajero entidad = new Pasajero();
        
        if (dto.getId() != null) {
            entidad.setId(new ObjectId(dto.getId()));
        }
        
        if (dto.getReservaciones()!= null) {
            List<Reservacion> reservacionesEntidad = dto.getReservaciones().stream()
                    .map(adaptadorReservacion::toEntity)
                    .collect(Collectors.toList());
            entidad.setReservaciones(reservacionesEntidad);
        } else {
            entidad.setReservaciones(new java.util.ArrayList<>());
        }

        entidad.setNombre(dto.getNombre());
        entidad.setCalificacion(dto.getCalificacion());

        return entidad;
    }
    
}

package adaptadores;

import dto.VehiculoDTO;
import java.util.ArrayList;
import java.util.List;
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

    public List<VehiculoDTO> ListaVehiculoToDTO(List<Vehiculo> vehiculo) {
        List<VehiculoDTO> vehiculosDTO = new ArrayList();
        for (Vehiculo v : vehiculo) {
            VehiculoDTO dto = new VehiculoDTO();
            dto.setCapacidad(v.getCapacidad());
            dto.setColor(v.getColor());
            dto.setMarca(v.getMarca());
            dto.setModelo(v.getModelo());
            dto.setPlacas(v.getPlacas());
            vehiculosDTO.add(dto);
        }

        return vehiculosDTO;
    }

    public List<Vehiculo> ListaVehiculoToEntity(List<VehiculoDTO> vehiculo) {
        List<Vehiculo> vehiculosEnt = new ArrayList();
        for (VehiculoDTO v : vehiculo) {
            Vehiculo ent = new Vehiculo();

            ent.setCapacidad(v.getCapacidad());
            ent.setColor(v.getColor());
            ent.setMarca(v.getMarca());
            ent.setModelo(v.getModelo());
            ent.setPlacas(v.getPlacas());

            vehiculosEnt.add(ent);
        }
        return vehiculosEnt;

    }
}

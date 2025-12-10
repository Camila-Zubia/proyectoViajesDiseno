/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adaptadores;

import DTO.PropietarioHaciendaDTO;
import DTO.VehiculoHaciendaDTO;
import dto.PropietarioDTO;
import dto.VehiculoDTO;

/**
 *
 * @author adell
 */
public class adaptadorHacienda {

    public static PropietarioHaciendaDTO toPropietarioHaciendaDTO(PropietarioDTO propietarioDTO) {
        if (propietarioDTO == null) {
            return null;
        }

        // Creamos el DTO de destino
        PropietarioHaciendaDTO haciendaDTO = new PropietarioHaciendaDTO();

        // Copiamos los campos relevantes para la validación de Hacienda
        haciendaDTO.setCurp(propietarioDTO.getCurp());
        haciendaDTO.setNombre(propietarioDTO.getNombre());
        haciendaDTO.setRfc(propietarioDTO.getRfc());
        haciendaDTO.setNss(propietarioDTO.getNss());

        // Nota: Los vehículos se mapean por separado o se asume que solo necesitamos los datos del propietario.
        // Si HaciendaDTO necesita la lista de vehículos, aquí deberías añadir:
        // haciendaDTO.setVehiculos(mapVehiculos(propietarioDTO.getListaVehiculos()));
        return haciendaDTO;
    }

    /**
     * Convierte un VehiculoDTO de la capa de Negocio/Control a un
     * VehiculoHaciendaDTO que es usado por el servicio de validación de
     * Hacienda.
     *
     * * @param vehiculoDTO El DTO del vehículo desde el Control/UI.
     * @param vehiculoDTO
     * @return El DTO específico para la capa de Hacienda.
     */
    public static VehiculoHaciendaDTO toVehiculoHaciendaDTO(VehiculoDTO vehiculoDTO) {
        if (vehiculoDTO == null) {
            return null;
        }

        VehiculoHaciendaDTO haciendaDTO = new VehiculoHaciendaDTO();

        // Copiamos los campos relevantes para la validación de Hacienda
        haciendaDTO.setNumeroSerie(vehiculoDTO.getNumeroSerie());
        haciendaDTO.setPlacas(vehiculoDTO.getPlacas());
        haciendaDTO.setMarca(vehiculoDTO.getMarca());
        haciendaDTO.setModelo(vehiculoDTO.getModelo());
        haciendaDTO.setColor(vehiculoDTO.getColor());
        haciendaDTO.setCapacidad(vehiculoDTO.getCapacidad());

        return haciendaDTO;
    }
}

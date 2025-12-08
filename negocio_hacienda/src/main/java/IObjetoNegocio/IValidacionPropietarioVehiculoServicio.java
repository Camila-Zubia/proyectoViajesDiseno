/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IObjetoNegocio;

import DTO.PropietarioHaciendaDTO;
import DTO.VehiculoHaciendaDTO;

/**
 *
 * @author adell
 */
public interface IValidacionPropietarioVehiculoServicio {
    boolean verificarCoincidencia(PropietarioHaciendaDTO propietarioDTO, VehiculoHaciendaDTO vehiculoDTO);
}

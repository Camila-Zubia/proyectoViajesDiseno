/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetoNegocio;

import DTO.PropietarioHaciendaDTO;
import DTO.VehiculoHaciendaDTO;
import IObjetoNegocio.IValidacionPropietarioVehiculoServicio;

/**
 *
 * @author adell
 */
public class FValidacionPropietarioVehiculoServicio implements IValidacionPropietarioVehiculoServicio{
    private final ControlValidacionPropietarioVehiculoServicio controlValidacion;

    public FValidacionPropietarioVehiculoServicio() {
        controlValidacion = new ControlValidacionPropietarioVehiculoServicio();
    }
    
    
    @Override
    public boolean verificarCoincidencia(PropietarioHaciendaDTO propietarioDTO, VehiculoHaciendaDTO vehiculoDTO) {
      return  controlValidacion.verificarCoincidencia(propietarioDTO, vehiculoDTO);
    }

    @Override
    public boolean existeVehiculoEnBD(VehiculoHaciendaDTO dto) {
        return controlValidacion.existeVehiculoEnBD(dto);
    }
    
}

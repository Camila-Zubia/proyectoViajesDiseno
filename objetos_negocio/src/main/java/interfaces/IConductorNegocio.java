/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dto.RutaFrecuenteDTO;
import dto.VehiculoDTO;
import dto.ViajeDTO;
import java.util.List;

/**
 *
 * @author Camila Zubia 00000244825
 */
public interface IConductorNegocio {
    
    public void agregarVehiculo(VehiculoDTO vehiculo);
    
    public List<VehiculoDTO> obtenerVehiculos();
    
    public void agregarViaje(ViajeDTO viaje);
    
    public List<ViajeDTO> obtenerViajes();
    public List<RutaFrecuenteDTO> obtenerRutas();
}

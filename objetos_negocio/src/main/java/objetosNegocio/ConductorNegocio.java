/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import dto.ConductorDTO;
import dto.VehiculoDTO;
import dto.ViajeDTO;
import java.util.List;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class ConductorNegocio {
    
    private ConductorDTO conductor;

    public ConductorNegocio() {
        conductor =  UsuarioNegocio.obtenerConductor();
    }
    
    public void agregarVehiculo(VehiculoDTO vehiculo){
        this.conductor.getVehiculos().add(vehiculo);
    }
    
    public List<VehiculoDTO> obtenerVehiculos(){
        return this.conductor.getVehiculos();
    }
    
    public void agregarViaje(ViajeDTO viaje){
        this.conductor.getViajes().add(viaje);
    }
    
    public List<ViajeDTO> obtenerViajes(){
        return this.conductor.getViajes();
    }
}

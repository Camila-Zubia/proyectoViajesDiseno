/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import dto.VehiculoDTO;
import java.util.List;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class VehiculoNegocio {
    
    List<VehiculoDTO> vehiculos = UsuarioNegocio.obtenerConductor().getVehiculos();
    
    public VehiculoNegocio() {
        vehiculos.add(new VehiculoDTO("Civic 2020", "ABC-123", "Honda", "Blanco", 4));
        vehiculos.add(new VehiculoDTO("Corolla 2021", "XYZ-789", "Toyota", "Gris", 4));
        vehiculos.add(new VehiculoDTO("Jetta 2019", "DEF-456", "Volkswagen", "Negro", 4));
    }
    
    public List<VehiculoDTO> obtenerVehiculos(){
        return vehiculos;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registrarVehiculo;

import dto.VehiculoDTO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adell
 */
public class FRegistrarVehiculo implements IRegistrarVehiculo {

    private final controlRegistrarVehiculo controlVehiculo;

    public FRegistrarVehiculo() {
        controlVehiculo = new controlRegistrarVehiculo();
    }

    @Override
    public boolean guardarDatosVehiculo(String numeroSerie, String modelo, String placas, String marca, String color, int capacidad) {
        return controlVehiculo.guardarDatosVehiculo( numeroSerie, modelo, placas, marca, color, capacidad);
    }

    @Override
    public void guardarDatosPropietario(String nombre, String curp, String rfc, String nss) {
        controlVehiculo.guardarDatosPropietario(nombre, curp, rfc, nss);
    }

    @Override
    public boolean confirmarRegistroVehiculoPropietario() {
        try {
           return controlVehiculo.confirmarRegistroVehiculoPropietario();
        } catch (Exception ex) {
            Logger.getLogger(FRegistrarVehiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public void eliminarVehiculo(VehiculoDTO dto) throws Exception{
    
        controlVehiculo.eliminarVehiculo(dto);
    }
    
    @Override
    public boolean eliminarVehiculoDeConductor (String numeroSerie){
        return controlVehiculo.eliminarVehiculoDeConductor(numeroSerie);
    }
    
    @Override
    public List<VehiculoDTO> obtenerListaVehiculos(){
    
        return controlVehiculo.obtenerListaVehiculos();
    }
}

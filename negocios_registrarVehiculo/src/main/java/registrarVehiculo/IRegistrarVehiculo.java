/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package registrarVehiculo;

import dto.VehiculoDTO;
import java.util.List;

/**
 *
 * @author adell
 */
public interface IRegistrarVehiculo {

    public boolean guardarDatosVehiculo(String numeroSerie, String modelo, String placas, String marca, String color, int capacidad);

    public void guardarDatosPropietario(String nombre, String curp, String rfc, String nss);

    public void confirmarRegistroVehiculoPropietario();

    public void eliminarVehiculo(VehiculoDTO dto) throws Exception;

    public boolean eliminarVehiculoDeConductor(String numeroSerie);
    
    public List<VehiculoDTO> obtenerListaVehiculos();
}

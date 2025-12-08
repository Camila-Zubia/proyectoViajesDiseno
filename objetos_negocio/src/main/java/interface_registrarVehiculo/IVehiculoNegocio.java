/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interface_registrarVehiculo;

import dto.VehiculoDTO;

/**
 * interfaz que tiene los metodos de vehiculo
 * @author adell
 */
public interface IVehiculoNegocio {
    /**
     * metodo que se implementa para persistir un vehiculo
     * @param vehiculo objeto que se persistira
     */
    public void registrarVehiculo(VehiculoDTO vehiculo);
}

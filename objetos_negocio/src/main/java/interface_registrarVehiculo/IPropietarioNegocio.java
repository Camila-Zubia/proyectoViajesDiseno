/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interface_registrarVehiculo;

import dto.PropietarioDTO;

/**
 * interfaz que tiene los metodos de vehiculo negocio
 * @author adell
 */
public interface IPropietarioNegocio {
    /**
     * metodo implementado que se encarga de persisitir un propietario
     * @param propietario objeto a persistir
     */
    public void registrarPropietario(PropietarioDTO propietario);
}

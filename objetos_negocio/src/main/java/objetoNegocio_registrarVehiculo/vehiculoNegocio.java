/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetoNegocio_registrarVehiculo;

import dto.VehiculoDTO;
import org.base_datos_viajes.dao.interfaces.IVehiculoDAO;
import org.base_datos_viajes.model.Vehiculo;

/**
 *
 * @author adell
 */
public class vehiculoNegocio {

    private final IVehiculoDAO vehiculoDAO;

    public vehiculoNegocio(IVehiculoDAO vehiculoDAO) {
        this.vehiculoDAO = vehiculoDAO;
    }

    
    public void registrarVehiculo(VehiculoDTO vehiculo) {
        Vehiculo entity = adaptadores.adaptadorVehiculo.toEntity(vehiculo);
        vehiculoDAO.save(entity);
    }

}

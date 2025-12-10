/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetoNegocio_registrarVehiculo;

import dto.VehiculoDTO;
import interface_registrarVehiculo.IVehiculoNegocio;
import org.base_datos_viajes.dao.interfaces.IVehiculoDAO;
import org.base_datos_viajes.model.Vehiculo;

/**
 * clase que implementa los metodos de ivehiculonegocio
 *
 * @author adell
 */
public class vehiculoNegocio implements IVehiculoNegocio {

    private final IVehiculoDAO vehiculoDAO;

    /**
     * constructor
     *
     * @param vehiculoDAO
     */
    public vehiculoNegocio(IVehiculoDAO vehiculoDAO) {
        this.vehiculoDAO = vehiculoDAO;
    }

    /**
     * metodo que hace la llamada a la dao para persistir un vehiculo
     *
     * @param vehiculo objeto a persistir
     */
    @Override
    public void registrarVehiculo(VehiculoDTO vehiculo) {
        Vehiculo entity = adaptadores.adaptadorVehiculo.toEntity(vehiculo);
        vehiculoDAO.save(entity);
    }

    @Override
    public void eliminarVehiculo(VehiculoDTO vehiculo) {
        Vehiculo entity = adaptadores.adaptadorVehiculo.toEntity(vehiculo);
        vehiculoDAO.delete(entity);
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import adaptadores.adaptadorVehiculo;
import dto.VehiculoDTO;
import dto.ViajeDTO;
import interfaces.IConductorNegocio;
import java.util.List;
import org.base_datos_viajes.dao.impl.ConductorDAO;
import utilidades.SesionUsuario;
import org.base_datos_viajes.dao.interfaces.IConductorDAO;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class ConductorNegocio implements IConductorNegocio {

    IConductorDAO conductoDAO = new ConductorDAO();
    adaptadorVehiculo adaptadorV = new adaptadorVehiculo();

    public ConductorNegocio() {

    }

    @Override
    public void agregarVehiculo(VehiculoDTO vehiculo) {
        SesionUsuario.obtenerConductor().getVehiculos().add(vehiculo);

    }

    @Override
    public List<VehiculoDTO> obtenerVehiculos() {
        return adaptadorV.ListaVehiculoToDTO(conductoDAO.obtenerVehiculos(SesionUsuario.obtenerConductor().getId()));
    }

    @Override
    public void agregarViaje(ViajeDTO viaje) {
        SesionUsuario.obtenerConductor().getViajes().add(viaje);
    }

    @Override
    public List<ViajeDTO> obtenerViajes() {
        return SesionUsuario.obtenerConductor().getViajes();
    }
}

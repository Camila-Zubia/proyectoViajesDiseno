/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import adaptadores.adaptadorRutaFrecuente;
import adaptadores.adaptadorVehiculo;
import adaptadores.adaptadorViaje;
import dto.RutaFrecuenteDTO;
import dto.VehiculoDTO;
import dto.ViajeDTO;
import interfaces.IConductorNegocio;
import java.util.List;
import java.util.stream.Collectors;
import org.base_datos_viajes.dao.impl.UsuarioDAO;
import org.base_datos_viajes.dao.impl.ViajeDAO;
import org.base_datos_viajes.dao.interfaces.IConductorDAO;
import org.base_datos_viajes.exception.DatabaseException;
import org.bson.types.ObjectId;
import utilidades.SesionUsuario;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class ConductorNegocio implements IConductorNegocio{
    private final IConductorDAO conductorDAO;
    
    public ConductorNegocio(IConductorDAO conductorDAO, UsuarioDAO usuarioDAO, ViajeDAO viajeDAO) {
        this.conductorDAO = conductorDAO; 
    }
    
    @Override
    public void agregarVehiculo(VehiculoDTO vehiculo){
        SesionUsuario.obtenerConductor().getVehiculos().add(vehiculo);
    }
    
    @Override
    public List<VehiculoDTO> obtenerVehiculos(){
        try {
            // Se usa el id del conductor activo
            ObjectId conductorId = new ObjectId(SesionUsuario.obtenerConductor().getId());
            
            // 1. Consulta en la BD para obtener la lista de vehiculos 
            return conductorDAO.obtenerVehiculos(conductorId.toHexString()).stream()
                    .map(adaptadorVehiculo::toDTO)
                    .collect(Collectors.toList());
        } catch (DatabaseException e) {
            throw new IllegalStateException("Error al obtener vehiculos de la base de datos: " + e.getMessage());
        }
    }
    
    @Override
    public void agregarViaje(ViajeDTO viaje){
        SesionUsuario.obtenerConductor().getViajes().add(viaje);
    }
    
    @Override
    public List<ViajeDTO> obtenerViajes(){
        try {
            ObjectId conductorId = new ObjectId(SesionUsuario.obtenerConductor().getId());
            
            // 2. Consulta en la BD para obtener la lista de viajes guardados
            return conductorDAO.obtenerViajes(conductorId.toHexString()).stream()
                    .map(adaptadorViaje::toDTO)
                    .collect(Collectors.toList());
        } catch (DatabaseException e) {
            throw new IllegalStateException("Error al obtener viajes de la base de datos: " + e.getMessage());
        }
    }
    
    
    @Override
    public List<RutaFrecuenteDTO> obtenerRutas(){
         try {
            ObjectId conductorId = new ObjectId(SesionUsuario.obtenerConductor().getId());
            
      
            return  conductorDAO.obtenerRutasFrecuentes(conductorId.toHexString()).stream()
                    .map(adaptadorRutaFrecuente::toDTO)
                   .collect(Collectors.toList());
        } catch (DatabaseException e) {
            throw new IllegalStateException("Error al obtener las rutas frecuentes de la base de datos: " + e.getMessage());
        }
    
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package registrarViaje;

import dto.ConductorDTO;
import dto.ParadaDTO;
import dto.VehiculoDTO;
import dto.ViajeDTO;
import java.util.List;

/**
 *interfaz que define las operaciones del caso de uso Registrar Viaje
 * @author Camila Zubia 00000244825
 */
public interface IRegistrarViaje {
    
    /**
     * crea un nuevo viaje en el sistema
     * @param viaje objeto que contiene la info como origen, destino, fecha, etc.
     */
    public void crearViaje(ViajeDTO viaje);
    
    
    /**
     * permite obtener el objeto vehiculo que fue seleccionado, para agregarlo
     * al viaje
     * @param vehiculo contiene la marca, color capacidad, etc.
     * @return regresa el vehiculo seleccionado
     */
    //public VehiculoDTO obtenerVehiculo(VehiculoDTO vehiculo);
    
    /**
     * recibe la lisra de vehiculos que el conductor tiene registrados
     * @param conductor
     * @return la lista de vehiculos
     */
    public List<VehiculoDTO> obtenerVehiculos(ConductorDTO conductor);
    
    /**
     * valida que el viaje que se esta registrando, no exista en el sistema
     * @return un bollean true = existe, false = no existe
     */
    //public boolean validarNoExiste();
    
    /**
     * agrega las paradas a la lista del viaje que se esta registrando
     * @param paradas lista
     */
    //public void agregarAListaParadas(List<ParadaDTO> paradas);
    
    /**
     * crea una nueva parada y la asocia al viaje actual
     * @param parada objeto que tiene la direccion y el precio
     */
    public void crearParada(ParadaDTO parada);

    /**
     * obtiene los viajes asociados a un conductor específico
     * @param conductor
     * @return lista de viajes del conductor
     */
    public List<ViajeDTO> obtenerViajes(ConductorDTO conductor);
    
    /**
     * 
     * @param viaje
     * @return 
     */
    public List<ParadaDTO> obtenerParadas(ViajeDTO viaje);
}

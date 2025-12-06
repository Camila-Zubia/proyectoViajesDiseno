/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package registrarViaje;

import dto.ConductorDTO;
import dto.ParadaDTO;
import dto.VehiculoDTO;
import dto.ViajeDTO;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 *interfaz que define las operaciones del caso de uso Registrar Viaje
 * @author Camila Zubia 00000244825
 */
public interface IRegistrarViaje {
    
    /**
     * obtiene los vehiculos relacionados a un conductor
     * @param conductor
     * @return 
     */
    public List<VehiculoDTO> obtenerVehiculosDisponibles(ConductorDTO conductor);
    
    /**
     * obtiene un vehiculo que fue seleccionado de una lista
     * @param vehiculo 
     */
    public void seleccionarVehiculo(VehiculoDTO vehiculo);
    
    /**
     * almacena los datos del viaje
     * @param origen
     * @param destino 
     * @param fecha 
     * @param hora 
     */
    public void guardarDatosViaje(String origen, String destino, LocalDate fecha, LocalTime hora, double precio);
    
    /**
     * crea una parada y la agrega a la lista de paradas
     * @param direccion
     * @param precio 
     */
    public void agregarParada(String direccion, double precio);
    
    /**
     * obtiene las paradas relacionadas con un viaje
     * @return 
     */
    public List<ParadaDTO> obtenerParadasTemporales();
    
    /**
     * obtiene los viajes relacionados al conductor
     * @param conductor
     * @return 
     */
    public List<ViajeDTO> obtenerViajesPorConductor(ConductorDTO conductor);
    
    /**
     * valida todos los datos del viaje y registra el viaje completo
     * @return
     */
    public ViajeDTO confirmarViaje();

}

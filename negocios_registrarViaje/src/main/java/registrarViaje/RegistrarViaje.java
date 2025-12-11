/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
 *
 * @author Camila Zubia 00000244825
 */
public class RegistrarViaje implements IRegistrarViaje{
    
    private final ControlViaje control;

    public RegistrarViaje() {
        control = new ControlViaje();
    }

    @Override
    public List<VehiculoDTO> obtenerVehiculosDisponibles(ConductorDTO conductor) {
        return control.obtenerVehiculos(conductor);
    }

    @Override
    public VehiculoDTO seleccionarVehiculo(VehiculoDTO vehiculo) {
        return control.seleccionarVehiculo(vehiculo);
    }

    @Override
    public boolean guardarDatosViaje(String origen, String destino, LocalDate fecha, LocalTime hora, double precioBase) {
        return control.guardarDatosViaje(origen, destino, fecha, hora, precioBase);
    }

    @Override
    public ParadaDTO agregarParada(String direccion, double precio) {
        return control.agregarParada(direccion, precio);
    }

    @Override
    public List<ViajeDTO> obtenerViajesPorConductor(ConductorDTO conductor) {
        return control.obtenerViajesPorConductor(conductor);
    }

    @Override
    public ViajeDTO confirmarViaje() {
        return control.confirmarViaje();
    }

    @Override
    public List<ParadaDTO> obtenerParadasTemporales() {
        return control.obtenerParadasTemporales();
    }
    
    @Override
    public ViajeDTO getViajeTemporal() {
        return control.getViajeTemporal();
    }
    
    
}

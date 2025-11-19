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
    public void seleccionarVehiculo(VehiculoDTO vehiculo) {
        control.seleccionarVehiculo(vehiculo);
    }

    @Override
    public void guardarDatosViaje(String origen, String destino, LocalDate fecha, LocalTime hora) {
        control.guardarDatosViaje(origen, destino, fecha, hora);
    }

    @Override
    public void agregarParada(String direccion, double precio) {
        control.agregarParada(direccion, precio);
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

}

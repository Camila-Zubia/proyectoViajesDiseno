/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registrarViaje;

import dto.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class ControlViaje {
    
    private final IRegistrarViaje fachada;

    // Estado temporal
    private VehiculoDTO vehiculoSeleccionado;
    private final List<ParadaDTO> paradasTemporales;

    public ControlViaje() {
        this.fachada = new RegistrarViaje();
        this.paradasTemporales = new ArrayList<>();
    }

    // Gestión de Vehículos
    public List<VehiculoDTO> obtenerVehiculosDisponibles(ConductorDTO conductor) {
        return fachada.obtenerVehiculos(conductor);
    }

    // Gestion de Paradas
    public void agregarParada(String direccion, double precio) {
        if (direccion == null || direccion.isEmpty() || precio < 0) {
            throw new IllegalArgumentException("La dirección no puede estar vacía y el precio debe ser positivo.");
        }
        ParadaDTO parada = new ParadaDTO(direccion, precio);
        fachada.crearParada(parada);
        paradasTemporales.add(parada);
    }
    
    public List<ParadaDTO> obtenerParadas(ViajeDTO viaje) {
        return fachada.obtenerParadas(viaje);
    }

    public List<ParadaDTO> obtenerParadasTemporales() {
        return paradasTemporales;
    }

    // Consulta de Viajes
    public List<ViajeDTO> obtenerViajesPorConductor(ConductorDTO conductor) {
        if (conductor == null) {
            throw new IllegalArgumentException("El conductor no puede ser null");
        }
        return fachada.obtenerViajes(conductor);
    }

    // Registro del Viaje
    public ViajeDTO registrarViaje(String origen, String destino, double precioBase) {
        if (origen == null || origen.isEmpty() || destino == null || destino.isEmpty()) {
            throw new IllegalArgumentException("El origen y destino son obligatorios.");
        }

        if (vehiculoSeleccionado == null) {
            throw new IllegalStateException("Debe seleccionar un vehículo antes de registrar el viaje.");
        }

        // Calculo del precio total
        double precioTotal = precioBase;
        for (ParadaDTO parada : paradasTemporales) {
            precioTotal += parada.getPrecio();
        }

        ViajeDTO viaje = new ViajeDTO(
                origen,
                destino,
                new Date(),
                LocalTime.now(),
                precioTotal
        );

        fachada.crearViaje(viaje);
        paradasTemporales.clear();

        return viaje;
    }
}

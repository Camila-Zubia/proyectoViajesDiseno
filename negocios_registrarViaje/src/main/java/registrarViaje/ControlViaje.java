/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registrarViaje;

import dto.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import objetosNegocio.VehiculoNegocio;
import objetosNegocio.ViajeNegocio;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class ControlViaje {
    
    private ViajeNegocio viajeBO;
    private VehiculoNegocio vehiculoBO;

    // Estado temporal
    private VehiculoDTO vehiculoSeleccionado;
    private final List<ParadaDTO> paradasTemporales;
    private String origenTemporal;
    private String destinoTemporal;
    private double precioTemporal;
    private LocalDate fechaTemporal;
    private LocalTime horaTemporal;

    public ControlViaje() {
        this.viajeBO = new ViajeNegocio();
        this.vehiculoBO = new VehiculoNegocio();
        this.paradasTemporales = new ArrayList<>();
    }
    
    public void crearViaje(ViajeDTO viaje) {
        viajeBO.registrarViaje(viaje);
    }
    
    public ParadaDTO getOrigenTemporal(){
       return null;
    }
    
    public void crearParada(ViajeDTO viaje, ParadaDTO parada) {
        viajeBO.registrarParada(viaje, parada);
    }
    
    public List<VehiculoDTO> obtenerVehiculos(ConductorDTO conductor) {
        return vehiculoBO.obtenerVehiculos();
    }

    public void seleccionarVehiculo(VehiculoDTO vehiculo) {
        if (vehiculo == null) {
            throw new IllegalArgumentException("Debe seleccionar un vehículo válido.");
        }
        this.vehiculoSeleccionado = vehiculo;
    }

    // Gestión de datos del viaje
    public void guardarDatosViaje(String origen, String destino, LocalDate fecha, LocalTime hora) {
        this.origenTemporal = origen;
        this.destinoTemporal = destino;
        this.precioTemporal = 0;
        this.fechaTemporal = fecha;
        this.horaTemporal = hora;
    }
    
    // Gestion de Paradas
    public void agregarParada(String direccion, double precio) {
        if (direccion == null || direccion.isEmpty() || precio < 0) {
            throw new IllegalArgumentException("La dirección no puede estar vacía y el precio debe ser positivo.");
        }
        ParadaDTO parada = new ParadaDTO(direccion, precio);
        paradasTemporales.add(parada);
    }
    
    public List<ParadaDTO> obtenerParadas(ViajeDTO viaje) {
        return viajeBO.obtenerParadas();
    }

    public List<ParadaDTO> obtenerParadasTemporales() {
        return paradasTemporales;
    }

    // Consulta de Viajes
    public List<ViajeDTO> obtenerViajesPorConductor(ConductorDTO conductor) {
        if (conductor == null) {
            throw new IllegalArgumentException("El conductor no puede ser null");
        }
        return viajeBO.obtenerViajes(conductor);
    }

    // Registro del Viaje
    public ViajeDTO registrarViaje(String origen, String destino) {
        if (origen == null || origen.isEmpty() || destino == null || destino.isEmpty()) {
            throw new IllegalArgumentException("El origen y destino son obligatorios.");
        }

        if (vehiculoSeleccionado == null) {
            throw new IllegalStateException("Debe seleccionar un vehículo antes de registrar el viaje.");
        }

        // Calculo del precio total
        for (ParadaDTO parada : paradasTemporales) {
            precioTemporal += parada.getPrecio();
        }

        ViajeDTO viaje = new ViajeDTO(
                origen,
                destino,
                fechaTemporal,
                horaTemporal,
                precioTemporal
        );
        viaje.setParadas(paradasTemporales);
        crearViaje(viaje);
        paradasTemporales.clear();

        return viaje;
    }

    // Registrar viaje con datos guardados temporalmente
    public ViajeDTO confirmarViaje() {
        if (origenTemporal == null || destinoTemporal == null) {
            throw new IllegalStateException("Debe guardar los datos del viaje primero.");
        }
        if (paradasTemporales.isEmpty()) {
            throw new IllegalStateException("El viaje debe tener al menos una parada.");
        }
        return registrarViaje(origenTemporal, destinoTemporal);
    }
}

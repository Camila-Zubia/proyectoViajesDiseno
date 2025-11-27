/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registrarViaje;

import dto.*;
import factory.FabricaBOs;
import factory.IFabricaBOs;
import interfaces.IConductorNegocio;
import interfaces.IViajeNegocio;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class ControlViaje {
    
    private final IViajeNegocio viajeBO;
    private final IConductorNegocio conductorBO;
    private VehiculoDTO vehiculoSeleccionado;
    private final List<ParadaDTO> paradasTemporales;
    private final ViajeDTO viajeTemporal;

    public ControlViaje() {
        IFabricaBOs fabrica = new FabricaBOs();
        this.viajeBO = fabrica.crearViajeNegocio();
        this.conductorBO = fabrica.crearConductorNegocio();
        this.paradasTemporales = new ArrayList<>();
        this.viajeTemporal = new ViajeDTO();
    }
    
    public void crearParada(ViajeDTO viaje, ParadaDTO parada) {
        viajeBO.registrarParada(viaje, parada);
    }
    
    public List<VehiculoDTO> obtenerVehiculos(ConductorDTO conductor) {
        return conductorBO.obtenerVehiculos();
    }

    public void seleccionarVehiculo(VehiculoDTO vehiculo) {
        if (vehiculo == null) {
            throw new IllegalArgumentException("Debe seleccionar un vehículo válido.");
        }
        this.vehiculoSeleccionado = vehiculo;
    }

    // Gestión de datos del viaje
    public void guardarDatosViaje(String origen, String destino, LocalDate fecha, LocalTime hora) {
        this.viajeTemporal.setOrigen(origen);
        this.viajeTemporal.setDestino(destino);
        this.viajeTemporal.setFecha(fecha);
        this.viajeTemporal.setHora(hora);
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
        return viajeBO.obtenerParadas(viaje);
    }

    public List<ParadaDTO> obtenerParadasTemporales() {
        return paradasTemporales;
    }

    // Consulta de Viajes
    public List<ViajeDTO> obtenerViajesPorConductor(ConductorDTO conductor) {
        if (conductor == null) {
            throw new IllegalArgumentException("El conductor no puede ser null");
        }
        return conductorBO.obtenerViajes();
    }

    // Registrar viaje con datos guardados temporalmente
    public ViajeDTO confirmarViaje() {
        if (viajeTemporal.getOrigen() == null || viajeTemporal.getDestino() == null) {
            throw new IllegalStateException("Debe guardar los datos del viaje primero.");
        }
        if (paradasTemporales.isEmpty()) {
            throw new IllegalStateException("El viaje debe tener al menos una parada.");
        }

        if (vehiculoSeleccionado == null) {
            throw new IllegalStateException("Debe seleccionar un vehiiculo antes de registrar el viaje.");
        }

        double precioTotal = paradasTemporales.get(0).getPrecio();
        
        viajeTemporal.setPrecioTotal(precioTotal);
        viajeTemporal.setParadas(paradasTemporales);
        viajeTemporal.setVehiculo(vehiculoSeleccionado);

        viajeBO.registrarViaje(viajeTemporal);
        paradasTemporales.clear();

        return viajeTemporal;
    }
}

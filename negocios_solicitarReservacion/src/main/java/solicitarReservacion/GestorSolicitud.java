/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package solicitarReservacion;

import dto.EstatusReservacion;
import dto.ParadaDTO;
import dto.ReservacionDTO;
import dto.ViajeDTO;
import factory.FabricaBOs;
import factory.IFabricaBOs;
import interfaces.IViajeNegocio;
import interfaces_solicitarReservacion.IPasajeroNegocio;
import java.time.Duration;
import java.time.LocalDateTime;
import static java.time.LocalDateTime.now;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class GestorSolicitud {
    
    private ViajeDTO viajeSeleccionado;
    private ParadaDTO paradaSeleccionada;
    private final IPasajeroNegocio pasajeroBO;
    private final IViajeNegocio viajeBO;
    private final ReservacionDTO reservacionTemporal;

    public GestorSolicitud() {
        IFabricaBOs fabrica = new FabricaBOs();
        this.pasajeroBO = fabrica.crearPasajeroNegocio();
        this.viajeBO = fabrica.crearViajeNegocio();
        this.reservacionTemporal = new ReservacionDTO();
    }
    
    public List<ViajeDTO> obtenerViajes(){
        return viajeBO.obtenerTodosLosViajesDisponibles();
    }
    
    public List<ParadaDTO> obtenerParadas() {
        if (viajeSeleccionado == null) {
            throw new IllegalStateException("No se ha seleccionado un viaje para ver sus paradas.");
        }
        return viajeSeleccionado.getParadas();
    }
    
    public ViajeDTO seleccionarViaje(ViajeDTO viaje){
        if (viaje == null) {
            throw new IllegalArgumentException("Debe seleccionar un viaje válido.");
        }
        return this.viajeSeleccionado = viaje;
    }
    
    public ParadaDTO seleccionarParada(ParadaDTO parada){
        if (parada == null) {
            throw new IllegalArgumentException("Debe seleccionar una parada válida.");
        }
        return this.paradaSeleccionada = parada;
    }
    
    public ParadaDTO solicitarParada(String direccion) {
        ParadaDTO paradaSolicitada = new ParadaDTO(direccion, 0.0);
        return this.paradaSeleccionada = paradaSolicitada;
    }
    
    public ReservacionDTO obtenerReservacionTemporal(){
        if (viajeSeleccionado == null) {
            throw new IllegalStateException("Error: No hay viaje seleccionado en el gestor.");
        }
        if (paradaSeleccionada == null) {
            throw new IllegalStateException("Error: No hay parada seleccionada en el gestor.");
        }

        reservacionTemporal.setViaje(viajeSeleccionado);
        reservacionTemporal.setParada(paradaSeleccionada);

        double precioFinal = viajeSeleccionado.getPrecioTotal();

        if (!paradaSeleccionada.getDirección().equalsIgnoreCase(viajeSeleccionado.getOrigen())) {
            precioFinal = precioFinal + paradaSeleccionada.getPrecio();
        }

        reservacionTemporal.setPrecioTotal(precioFinal);

        LocalDateTime tiempo = LocalDateTime.of(viajeSeleccionado.getFecha(), viajeSeleccionado.getHora());
        reservacionTemporal.setTiempoRestante(Duration.between(now(), tiempo).toSeconds());
        reservacionTemporal.setEstatus(EstatusReservacion.ESPERA);

        return reservacionTemporal;
    }
    
    public ReservacionDTO confirmarReservacion(){
        if (reservacionTemporal.getViaje() == null || reservacionTemporal.getParada() == null) {
            obtenerReservacionTemporal();
        }
        pasajeroBO.agregarReservacion(reservacionTemporal);
        return reservacionTemporal;
    }
    
}

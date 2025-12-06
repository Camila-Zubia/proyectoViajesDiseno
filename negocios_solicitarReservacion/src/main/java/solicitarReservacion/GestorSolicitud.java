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
        return viajeSeleccionado.getParadas();
    }
    
    public void seleccionarViaje(ViajeDTO viaje){
        if (viaje == null) {
            throw new IllegalArgumentException("Debe seleccionar un viaje válido.");
        }
        this.viajeSeleccionado = viaje;
    }
    
    public void seleccionarParada(ParadaDTO parada){
        if (parada == null) {
            throw new IllegalArgumentException("Debe seleccionar una parada válida.");
        }
        this.paradaSeleccionada = parada;
    }
    
    public ReservacionDTO obtenerReservacionTemporal(){
        reservacionTemporal.setViaje(viajeSeleccionado);
        reservacionTemporal.setParada(paradaSeleccionada);
        if (viajeSeleccionado.getParadas().get(0).getDirección() == null ? paradaSeleccionada.getDirección() != null : !viajeSeleccionado.getParadas().get(0).getDirección().equals(paradaSeleccionada.getDirección())) {
            double precio = viajeSeleccionado.getPrecioTotal() + paradaSeleccionada.getPrecio();
            reservacionTemporal.setPrecioTotal(precio);
        } else {
            reservacionTemporal.setPrecioTotal(viajeSeleccionado.getPrecioTotal());
        }
        LocalDateTime tiempo = LocalDateTime.of(viajeSeleccionado.getFecha(), viajeSeleccionado.getHora());
        reservacionTemporal.setTiempoRestante(Duration.between(now(), tiempo).toSeconds());
        reservacionTemporal.setEstatus(EstatusReservacion.ESPERA);
        return reservacionTemporal;
    }
    
    public ReservacionDTO confirmarReservacion(){
        if (viajeSeleccionado == null) {
            throw new IllegalStateException("Debe seleccionar un viaje antes de realizar la reservación");
        }
        if (paradaSeleccionada == null) {
            throw new IllegalStateException("Debe seleccionar una parada antes de realizar la reservación");
        }
        pasajeroBO.agregarReservacion(reservacionTemporal);
        return reservacionTemporal;
    }
    
    public void solicitarParada(String direccion){
        ParadaDTO paradaSolicitada = new ParadaDTO();
        paradaSolicitada.setDirección(direccion);
        this.paradaSeleccionada = paradaSolicitada;
    }
    
}

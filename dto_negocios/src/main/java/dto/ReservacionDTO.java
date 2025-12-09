/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.Duration;
import static java.time.LocalDateTime.now;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class ReservacionDTO {
    
    private String Id;
    private ViajeDTO viaje;
    private double precioTotal;
    private ParadaDTO parada;
    private EstatusReservacion estatus;
    private Long tiempoRestante;
    private PasajeroDTO pasajero;

    public ReservacionDTO() {
    }

    public ReservacionDTO(ViajeDTO viaje, ParadaDTO parada) {
        this.viaje = viaje;
        this.parada = parada;
        this.precioTotal = 0;
        LocalDateTime tiempo = LocalDateTime.of(viaje.getFecha(), viaje.getHora());
        Duration diferencia = Duration.between(now(), tiempo);
        this.tiempoRestante = diferencia.toSeconds();
        this.estatus = EstatusReservacion.ESPERA;
    }

    public ViajeDTO getViaje() {
        return viaje;
    }

    public void setViaje(ViajeDTO viaje) {
        this.viaje = viaje;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public ParadaDTO getParada() {
        return parada;
    }

    public void setParada(ParadaDTO parada) {
        this.parada = parada;
    }

    public EstatusReservacion getEstatus() {
        return estatus;
    }

    public void setEstatus(EstatusReservacion estatus) {
        this.estatus = estatus;
    }

    public Long getTiempoRestante() {
        return tiempoRestante;
    }

    public void setTiempoRestante(Long tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public PasajeroDTO getPasajero() {
        return pasajero;
    }

    public void setPasajero(PasajeroDTO pasajero) {
        this.pasajero = pasajero;
    }
    
    
    
    @Override
    public String toString() {
        return String.format(viaje.getOrigen() + "--->" + viaje.getDestino()
        + ", " + parada.getDirección()
        + "     " + viaje.getFecha().getDayOfMonth() + "/" + viaje.getFecha().getMonth().getDisplayName(TextStyle.SHORT, Locale.ITALY)
        + ", " + viaje.getHora()
        + "     $" + precioTotal
        + "     " + estatus);
    }
    
}

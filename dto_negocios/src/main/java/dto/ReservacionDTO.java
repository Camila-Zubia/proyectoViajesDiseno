/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class ReservacionDTO {
    
    enum Estatus{
        ESPERA, ACEPTADA, RECHAZADA, CANCELADA, TERMINADA;
    }
    
    private ViajeDTO viaje;
    private double precioTotal;
    private ParadaDTO parada;
    private Estatus estatus;

    public ReservacionDTO() {
    }

    public ReservacionDTO(ViajeDTO viaje, ParadaDTO parada) {
        this.viaje = viaje;
        this.parada = parada;
        this.precioTotal = 0;
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

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }

    @Override
    public String toString() {
        return "ReservacionDTO{" + "viaje=" + viaje + ", precioTotal=" + precioTotal + ", parada=" + parada + ", estatus=" + estatus + '}';
    }
    
    
    
}

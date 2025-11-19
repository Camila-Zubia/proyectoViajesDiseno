/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class ViajeDTO {
    
    private String origen;
    private String destino;
    private LocalDate fecha;
    private LocalTime hora;
    private double precioTotal;
    private List<ParadaDTO> paradas;
    private VehiculoDTO vehiculo;

    public ViajeDTO() {
        this.paradas = new ArrayList<>();
    }

    public ViajeDTO(String origen, String destino, LocalDate fecha, LocalTime hora, double precioTotal) {
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.hora = hora;
        this.precioTotal = precioTotal;
        this.paradas = new ArrayList<>();
    }

    public ViajeDTO(String origen, String destino, LocalDate fecha, LocalTime hora, double precioTotal, List<ParadaDTO> paradas) {
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.hora = hora;
        this.precioTotal = precioTotal;
        this.paradas = paradas;
    }

    public ViajeDTO(String origen, String destino, LocalDate fecha, LocalTime hora, double precioTotal, List<ParadaDTO> paradas, VehiculoDTO vehiculo) {
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.hora = hora;
        this.precioTotal = precioTotal;
        this.paradas = paradas;
        this.vehiculo = vehiculo;
    }
    
    
    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public List<ParadaDTO> getParadas() {
        return paradas;
    }

    public void setParadas(List<ParadaDTO> paradas) {
        this.paradas = paradas;
    }

    public VehiculoDTO getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(VehiculoDTO vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Override
    public String toString() {
        return origen + "-->" + destino + 
                ", fecha = " + fecha.getDayOfMonth() + "/" + fecha.getMonth().getDisplayName(TextStyle.SHORT, Locale.ITALY)+
                ", hora = " + hora +
                ", $" + precioTotal;
    }

}

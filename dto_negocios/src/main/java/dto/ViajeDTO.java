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
import java.util.Objects;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class ViajeDTO {

    private String id;
    private String origen;
    private String destino;
    private LocalDate fecha;
    private LocalTime hora;
    private double precioTotal;
    private List<ParadaDTO> paradas;
    private VehiculoDTO vehiculo;
    private boolean estaActivo;
    private int cantidadPasajeros;

    public ViajeDTO() {
        this.paradas = new ArrayList<>();
        this.estaActivo = true;
        this.cantidadPasajeros = 0;
    }

    public ViajeDTO(String origen, String destino, LocalDate fecha, LocalTime hora, double precioTotal) {
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.hora = hora;
        this.precioTotal = precioTotal;
        this.paradas = new ArrayList<>();
        this.estaActivo = true;
        this.cantidadPasajeros = 0;
    }

    public ViajeDTO(String origen, String destino, LocalDate fecha, LocalTime hora, double precioTotal, List<ParadaDTO> paradas) {
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.hora = hora;
        this.precioTotal = precioTotal;
        this.paradas = paradas;
        this.estaActivo = true;
        this.cantidadPasajeros = 0;
    }

    public ViajeDTO(String origen, String destino, LocalDate fecha, LocalTime hora, double precioTotal, List<ParadaDTO> paradas, VehiculoDTO vehiculo) {
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.hora = hora;
        this.precioTotal = precioTotal;
        this.paradas = paradas;
        this.vehiculo = vehiculo;
        this.estaActivo = true;
        this.cantidadPasajeros = 0;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isEstaActivo() {
        return estaActivo;
    }

    public void setEstaActivo(boolean estaActivo) {
        this.estaActivo = estaActivo;
    }

    public int getCantidadPasajeros() {
        return cantidadPasajeros;
    }

    public void setCantidadPasajeros(int cantidadPasajeros) {
        this.cantidadPasajeros = cantidadPasajeros;
    }


    @Override
    public String toString() {
        return String.format(origen + " ---> " + destino
                + "     fecha = " + fecha.getDayOfMonth() + "/" + fecha.getMonth().getDisplayName(TextStyle.SHORT, Locale.ITALY) 
                + ", hora = " + hora
                + "     $" + precioTotal);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ViajeDTO other = (ViajeDTO) obj;
        if (!Objects.equals(this.origen, other.origen)) {
            return false;
        }
        if (!Objects.equals(this.destino, other.destino)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        return Objects.equals(this.hora, other.hora);
    }

   

}

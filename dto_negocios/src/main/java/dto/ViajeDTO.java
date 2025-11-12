/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Camila Zubia 00000244825
 */
public class ViajeDTO {
    
    private String origen;
    private String destino;
    private Date fecha;
    private LocalTime hora;
    private double precioTotal;
    private List<ParadaDTO> paradas;

    public ViajeDTO() {
        this.paradas = new ArrayList<>();
    }

    public ViajeDTO(String origen, String destino, Date fecha, LocalTime hora, double precioTotal) {
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.hora = hora;
        this.precioTotal = precioTotal;
        this.paradas = new ArrayList<>();
    }

    public ViajeDTO(String origen, String destino, Date fecha, LocalTime hora, double precioTotal, List<ParadaDTO> paradas) {
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.hora = hora;
        this.precioTotal = precioTotal;
        this.paradas = paradas;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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

    @Override
    public String toString() {
        return "Origen = " + origen + 
                ", destino = " + destino + 
                ", fecha = " + fecha.getDay()+ "/" + fecha.getMonth() + "/" +
                ", hora = " + hora + 
                ", precioTotal = " + precioTotal;
    }

}

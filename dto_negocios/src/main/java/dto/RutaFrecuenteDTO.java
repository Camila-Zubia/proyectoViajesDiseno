/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author adell
 */
public class RutaFrecuenteDTO {

    private String id;
    private String origen;
    private String destino;
    private LocalDate fecha;
    private LocalTime hora;
    private double precioTotal;
    private List<ParadaDTO> paradas;

    public RutaFrecuenteDTO() {
    }

    public RutaFrecuenteDTO(List<ParadaDTO> paradas) {
        this.paradas = paradas;
    }

    public RutaFrecuenteDTO(String origen, String destino, LocalDate fecha, LocalTime hora, double precioTotal, List<ParadaDTO> paradas) {
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.hora = hora;
        this.precioTotal = precioTotal;
        this.paradas = paradas;
    }

    public RutaFrecuenteDTO(String id, String origen, String destino, LocalDate fecha, LocalTime hora, double precioTotal, List<ParadaDTO> paradas) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.hora = hora;
        this.precioTotal = precioTotal;
        this.paradas = paradas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

}

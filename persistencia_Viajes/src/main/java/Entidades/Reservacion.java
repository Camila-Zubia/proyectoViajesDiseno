/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.time.Duration;

/**
 *
 * @author Usuario
 */
public class Reservacion {
    
    public enum Estatus {
        ESPERA, ACEPTADA, RECHAZADA, CANCELADA, TERMINADA;
    }
    
    private String id;
    private Viaje viaje;
    private Parada parada;
    private double costo;
    private Duration tiempoRestante;
    private Estatus estatus;

    public Reservacion() {
    }

    public Reservacion(String id, Viaje viaje, Parada parada, double costo, Duration tiempoRestante, Estatus estatus) {
        this.id = id;
        this.viaje = viaje;
        this.parada = parada;
        this.costo = costo;
        this.tiempoRestante = tiempoRestante;
        this.estatus = estatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    public Parada getParada() {
        return parada;
    }

    public void setParada(Parada parada) {
        this.parada = parada;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public Duration getTiempoRestante() {
        return tiempoRestante;
    }

    public void setTiempoRestante(Duration tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }
    
}

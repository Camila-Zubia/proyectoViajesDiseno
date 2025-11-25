/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.util.List;

/**
 *
 * @author Usuario
 */
public class Pasajero {
    
    private String id;
    private String nombre;
    private double calificacion;
    private List<Reservacion> reservaciones;

    public Pasajero() {
    }

    public Pasajero(String id, String nombre, double calificacion, List<Reservacion> reservaciones) {
        this.id = id;
        this.nombre = nombre;
        this.calificacion = calificacion;
        this.reservaciones = reservaciones;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public List<Reservacion> getReservaciones() {
        return reservaciones;
    }

    public void setReservaciones(List<Reservacion> reservaciones) {
        this.reservaciones = reservaciones;
    }
    
    
}
